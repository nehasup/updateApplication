package com.upskillutoday.crmRoot.response;

public class JwtResponse {

	private String token;
	private String type = "Bearer";
	private Long userId;
	private String userName;

	public JwtResponse(String accessToken, Long userId, String userName) {
		this.token = accessToken;
		this.userId = userId;
		this.userName = userName;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
