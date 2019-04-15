package com.coveiot.coveiotapp.dao;



public class UserDto {

	protected String name;
	protected String emailId;
	protected Integer pincode;
	public UserDto() {
		// TODO Auto-generated constructor stub
	}
	public UserDto(String name, String emailId, Integer pincode) {
		super();
		this.name = name;
		this.emailId = emailId;
		this.pincode = pincode;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public Integer getPincode() {
		return pincode;
	}
	public void setPincode(Integer pincode) {
		this.pincode = pincode;
	}
	@Override
	public String toString() {
		return "UserDto [name=" + name + ", emailId=" + emailId + ", pincode=" + pincode + "]";
	}
	
}
