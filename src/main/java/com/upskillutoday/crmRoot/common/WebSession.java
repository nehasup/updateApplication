package com.upskillutoday.crmRoot.common;

/**
 * The session container for the web application.  Should
 * not really contain much as most session related
 * data is usually a cross application requirement.
 * Only add stuff here that is really specific to web.
 */

public class WebSession extends AbstractSession {
    private LoginData loginData;

    public LoginData getLoginData() {
        return loginData;
    }

    public void setLoginData(LoginData loginData) {
        this.loginData = loginData;
    }
}
