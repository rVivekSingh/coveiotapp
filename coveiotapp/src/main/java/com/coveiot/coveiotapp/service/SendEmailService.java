/**
 * 
 */
package com.coveiot.coveiotapp.service;

import java.text.SimpleDateFormat;

import org.springframework.stereotype.Service;

import com.coveiot.coveiotapp.model.User;
import com.coveiot.coveiotapp.util.ConfigReader;
import com.coveiot.coveiotapp.util.SendEmail;

/**
 * @author vivek.rathor
 *
 */
@Service
public class SendEmailService {
	 static ConfigReader config = ConfigReader.getInstance();
	public String sendEmailForLoginSuccess(String emailId, String userId, String name) {
		try {
			String subject = "Registration Success";

			StringBuffer emailBody = new StringBuffer();

			String[] recipients = { "" };

			emailBody.append("<div style=\"padding:4px;margin-bottom:4px;\">Dear " + name
					+ ",</div><div>Your Registraion is successfull. Your Login id is :" + userId + "</div>");
			emailBody.append("<div style=\"padding:4px\">Thank you,<br><br>Best Regards,<br>Kaha Team</div>");
			recipients[0] = emailId;
			SendEmail.sentMailMessageTo( recipients, subject, emailBody.toString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return "SUCCESS";
	}

	public String sendEmailForUserDetails(Long id, User user) {
		try {
			String subject = "Login Success";

			StringBuffer emailBody = new StringBuffer();

			String[] recipients = { "" };
			
			String link = config.basePath + "userDeatils?id="+user.getId();

			emailBody.append("<div style=\"padding:4px;margin-bottom:4px;\">Dear " + user.getName()
					+ ",</div><div>Your Login is successfull. Click on this link to see Details :<br>" + link + "</div>");
			emailBody.append("<div style=\"padding:4px\">Thank you,<br><br>Best Regards,<br>Kaha Team</div>");
			recipients[0] = user.getEmailId();
			SendEmail.sentMailMessageTo( recipients, subject, emailBody.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
