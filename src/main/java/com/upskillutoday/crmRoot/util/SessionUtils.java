package com.upskillutoday.crmRoot.util;


import org.springframework.web.util.WebUtils;

import com.upskillutoday.crmRoot.common.WebSession;
import com.upskillutoday.crmRoot.common.constants.SessionConstants;

import javax.servlet.http.HttpServletRequest;
public class SessionUtils {
    public static WebSession getWebSession(HttpServletRequest request) {
        return (WebSession) WebUtils.getSessionAttribute(request, SessionConstants.WEB_SESSION);
    }

    public static void setWebSession(HttpServletRequest request, WebSession session) {
        WebUtils.setSessionAttribute(request, SessionConstants.WEB_SESSION, session);
    }
}
