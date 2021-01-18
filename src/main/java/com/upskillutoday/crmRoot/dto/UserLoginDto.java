package com.upskillutoday.crmRoot.dto;

public class UserLoginDto {

	private String contactNo;
    private String pass;
    private String loginMsg;
    private boolean flag;

	public UserLoginDto(String contactNo, String pass, String loginMsg, boolean flag) {
		super();
		this.contactNo = contactNo;
		this.pass = pass;
		this.loginMsg = loginMsg;
		this.flag = flag;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getLoginMsg() {
		return loginMsg;
	}

	public void setLoginMsg(String loginMsg) {
		this.loginMsg = loginMsg;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}
}
