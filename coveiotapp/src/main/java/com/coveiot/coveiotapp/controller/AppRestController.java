package com.coveiot.coveiotapp.controller;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.coveiot.coveiotapp.dao.LoginDto;
import com.coveiot.coveiotapp.dao.UserDto;
import com.coveiot.coveiotapp.model.Login;
import com.coveiot.coveiotapp.model.User;
import com.coveiot.coveiotapp.repo.LoginRepository;
import com.coveiot.coveiotapp.repo.UserRepository;
import com.coveiot.coveiotapp.service.SendEmailService;
import com.coveiot.coveiotapp.util.CoveiotUtil;

@RestController
public class AppRestController {

	@Autowired
	UserRepository repo;
	
	@Autowired
	LoginRepository loginRepo;
	
	@Autowired
    SendEmailService emailService;
	
	@RequestMapping(value = "/registerUser", method = RequestMethod.POST)
	public String saveUser(@RequestBody UserDto request) {
		String userId = CoveiotUtil.getUserId();
		User userModel = new User();
		Login login = new Login();
		userModel.setName(request.getName());
		userModel.setEmailId(request.getEmailId());
		userModel.setPincode(request.getPincode());
		login.setUserId(userId);
		login.setLoginStatus(true);
		userModel.setLogin(login);
		repo.save(userModel);
		emailService.sendEmailForLoginSuccess(userModel.getEmailId(), userId, userModel.getName());
		return "COV200";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginUser(@RequestBody LoginDto request) {
		String status = null;
		 Login loginDetails=loginRepo.findByUserId(request.getUserId());
		 if(loginDetails!=null) {
			 loginDetails.setLastLogin(new Timestamp(System.currentTimeMillis()));
			 loginDetails.setLoginExpire(15);
			 loginRepo.save(loginDetails);
		 status = "COV200";
		 User user = repo.findOne(loginDetails.getId());
		 System.err.println(user.getEmailId());
		 emailService.sendEmailForUserDetails(loginDetails.getId(), user);
		 }
		 else {
		 status = "COV600";
		 }
		 System.out.println("Login==="+loginDetails);

		return status;
	}
	
	@RequestMapping(value="/userDeatils", method= RequestMethod.GET)
	public User getUserDetails(@RequestParam Long id){
		User userDetails = null;
		Login loginDetails=loginRepo.findOne(id);
		if(loginDetails!= null)
		{
			int loginExpire = loginDetails.getLoginExpire();
			System.out.println("loginExpire=="+loginExpire);
			Date storedDate =loginDetails.getLastLogin();
			if(CoveiotUtil.checkLoginExpire(storedDate, loginExpire)){
				System.out.println("Hello session is Exit");
				userDetails= repo.findOne(loginDetails.getId());
			}
			else {
				System.out.println("Hello session Not is Exit");
			}
			
		}
		else {
			return userDetails;
		}
		return userDetails;
	}

}
