package com.upskillutoday.crmRoot.request;



public class EmpLoginReqDto {
	private String userName;
	private String pass;

	public String getUserName() {
		return userName;
	}

	public EmpLoginReqDto(String userName, String pass) {
		this.userName = userName;
		this.pass = pass;
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
}
