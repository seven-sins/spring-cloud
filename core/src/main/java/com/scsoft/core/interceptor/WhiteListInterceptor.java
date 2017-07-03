package com.scsoft.core.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class WhiteListInterceptor extends HandlerInterceptorAdapter {
	
	private static Logger log = LoggerFactory.getLogger(WhiteListInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		log.debug(request.getRemoteAddr());
		
		return true;
	}
}
