package com.coveiot.coveiotapp.dao;

public class LoginDto {
	protected String userId;
public LoginDto() {
	// TODO Auto-generated constructor stub
}
	public LoginDto(String userId) {
		super();
		this.userId = userId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	
}
