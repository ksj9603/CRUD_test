package com.example.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BoardInterceptor implements HandlerInterceptor{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		log.info("BoardInterceptor preHandler");
		
		HttpSession session = request.getSession();
		
		if(session.getAttribute("loginSession") == null ) {
			response.sendRedirect("/");
		}
		
		return true;
	}
}
