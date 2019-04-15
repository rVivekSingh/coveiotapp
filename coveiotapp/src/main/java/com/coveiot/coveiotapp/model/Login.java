package com.coveiot.coveiotapp.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "login")
public class Login {
	@Id
	@Column(name = "id", updatable = false, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long Id;
	
	@Column(name = "user_id", nullable = false)
	protected String userId;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_login")
	protected Date lastLogin;
	

	@Column(name = "login_expire")
	protected Integer loginExpire;
	
	
	 @Column(name="login_status",nullable= false)
	    protected boolean loginStatus;
public Login() {
	// TODO Auto-generated constructor stub
}

	public Long getId() {
		return Id;
	}


	public void setId(Long id) {
		Id = id;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public Date getLastLogin() {
		return lastLogin;
	}


	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}


	public Integer getLoginExpire() {
		return loginExpire;
	}


	public void setLoginExpire(Integer loginExpire) {
		this.loginExpire = loginExpire;
	}


	public boolean isLoginStatus() {
		return loginStatus;
	}


	public void setLoginStatus(boolean loginStatus) {
		this.loginStatus = loginStatus;
	}

	@Override
	public String toString() {
		return "Login [Id=" + Id + ", userId=" + userId + ", lastLogin=" + lastLogin + ", loginExpire=" + loginExpire
				+ ", loginStatus=" + loginStatus + "]";
	}
	 
	 
}


