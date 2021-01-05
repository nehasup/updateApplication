package com.upskillutoday.crmRoot.common;

import java.io.Serializable;


/**
 * Session Container for all web session related information.
 * all web session parameters should be stored here.
 */
public abstract class AbstractSession implements Serializable {

private String email;
private String contactName;
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getContactName() {
	return contactName;
}
public void setContactName(String contactName) {
	this.contactName = contactName;
}
}
