package com.blogmaker.helper;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpSession;

@Component
public class SessionAttributeRemoverDelay {

	
	  public static void removeAttributeAfterDelay(HttpSession session, String attributeName, int delayInSeconds) {
	        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
	        scheduler.schedule(() -> {
	            session.removeAttribute(attributeName);
	            scheduler.shutdown();
	        }, delayInSeconds, TimeUnit.SECONDS);
	    }
	
	
	
	
	
	
	
	
	
}
