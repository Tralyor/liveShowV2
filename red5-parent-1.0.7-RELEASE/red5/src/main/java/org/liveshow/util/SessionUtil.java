package org.liveshow.util;

import org.liveshow.entity.Tuser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUtil {
    public static Tuser getUserInSession(HttpServletRequest request){
        HttpSession httpSession = request.getSession();
        return (Tuser)httpSession.getAttribute("user");
    }
}
