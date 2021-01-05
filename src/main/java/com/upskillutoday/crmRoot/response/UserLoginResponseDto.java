package com.upskillutoday.crmRoot.response;

import java.util.Date;



public class UserLoginResponseDto {
	
	private long userId;
	private String fullName;
	private String emailId;
	private String contactNo;
	private Date birthDate;
	private String gender;
	private String city;
	private String userName;
	private String pass;
	private String loginStatus;
	private int updatedBy;
	private Date updatedOn;
	private boolean deletedFlag;
	
	
	
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public int getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(int updatedBy) {
		this.updatedBy = updatedBy;
	}
	public Date getUpdatedOn() {
		return updatedOn;
	}
	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}
	public boolean isDeletedFlag() {
		return deletedFlag;
	}
	public void setDeletedFlag(boolean deletedFlag) {
		this.deletedFlag = deletedFlag;
	}
	public String getLoginStatus() {
		return loginStatus;
	}
	public void setLoginStatus(String loginStatus) {
		this.loginStatus = loginStatus;
	}
	
	public UserLoginResponseDto(long userId, String fullName, String emailId, String contactNo, Date birthDate,
			String gender, String city, String userName, String pass, String loginStatus, int updatedBy, Date updatedOn,
			boolean deletedFlag) {
		super();
		this.userId = userId;
		this.fullName = fullName;
		this.emailId = emailId;
		this.contactNo = contactNo;
		this.birthDate = birthDate;
		this.gender = gender;
		this.city = city;
		this.userName = userName;
		this.pass = pass;
		this.loginStatus = loginStatus;
		this.updatedBy = updatedBy;
		this.updatedOn = updatedOn;
		this.deletedFlag = deletedFlag;
	}

	
	
	
	
	
	
	


}
