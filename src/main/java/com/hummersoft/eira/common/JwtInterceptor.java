package com.hummersoft.eira.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtInterceptor implements HandlerInterceptor {

	@Autowired
	private jwtUtil jwtutil;
	
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
    		throws Exception {
    	String auth = request.getHeader("authorization");
    	if(!(request.getRequestURI().contains("login")|| request.getRequestURI().contains("signup"))) {
    		jwtutil.verify(auth);
    	}
    	return HandlerInterceptor.super.preHandle(request, response, handler);
    }
   
}
