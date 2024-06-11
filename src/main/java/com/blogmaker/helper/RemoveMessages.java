package com.blogmaker.helper;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Component
public class RemoveMessages {

	
	
	
public void removeSessionAttribute()
{
	
	try {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        session.removeAttribute("msg");
//       SessionAttributeRemoverDelay.removeAttributeAfterDelay(session, "msg", 1); // Remove after 5 seconds
    } 
	catch (RuntimeException ex) {
        ex.printStackTrace();
    }
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
