package com.upskillutoday.crmRoot.util;


import org.springframework.web.util.WebUtils;

import com.upskillutoday.crmRoot.common.WebSession;
import com.upskillutoday.crmRoot.common.constants.SessionConstants;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @author tonyd
 *
 */
public class SessionUtils {



//	public static String getContactEmailId(HttpServletRequest request) {
//		return (String) WebUtils.getSessionAttribute(request, SessionConstants.EMAIL);
//	}


//	public static void setContactEmailId(HttpServletRequest request, String email) {
//		WebUtils.setSessionAttribute(request, SessionConstants.EMAIL, email);
//	}
//
//	public static void deleteContactEmailId(HttpServletRequest request) {
//		if (getContactEmailId(request) != null) {
//			request.getSession().removeAttribute(SessionConstants.EMAIL);
//		}
//
//	}
	
    public static WebSession getWebSession(HttpServletRequest request) {
        return (WebSession) WebUtils.getSessionAttribute(request, SessionConstants.WEB_SESSION);
    }

    public static void setWebSession(HttpServletRequest request, WebSession session) {
        WebUtils.setSessionAttribute(request, SessionConstants.WEB_SESSION, session);
    }

}
