package com.upskillutoday.crmRoot.request;

import java.util.Set;

public class SingupRequest {

	    private String username;
	 
	    
	    private Set<String> role;
	    
	    private String password;

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public Set<String> getRole() {
			return role;
		}

		public void setRole(Set<String> role) {
			this.role = role;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}
	  
	
}
