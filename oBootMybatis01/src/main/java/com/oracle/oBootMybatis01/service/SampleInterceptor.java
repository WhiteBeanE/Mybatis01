package com.oracle.oBootMybatis01.service;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class SampleInterceptor implements HandlerInterceptor {
	public SampleInterceptor() {
		// TODO Auto-generated constructor stub
	}
	// 2
	@Override
	public void postHandle( HttpServletRequest 	request, 
							HttpServletResponse response, 
							Object 				handler,
							ModelAndView 		modelAndView) throws Exception {
		// TODO Auto-generated method stub
		//HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
		System.out.println("SampleInterceptor.postHandle Start");
		String ID = (String)modelAndView.getModel().get("id");
		int memCnt = (Integer)modelAndView.getModel().get("memCnt");
		System.out.println("SampleInterceptor.postHandle memCnt -> " + memCnt);
		
		if(memCnt < 1) { // 비로그인 유저
			System.out.println("memCnt Not exists");
			request.getSession().setAttribute("ID", ID);
			// User가 존재하지 않으면 User interCeptor Page(회원등록) 이동
			response.sendRedirect("doMemberWrite");
		}else { // 로그인 유저
			System.out.println("memCnt exists");
			request.getSession().setAttribute("ID", ID);
			// User가 존재하면 User interCeptor Page(회원List) 이동
			response.sendRedirect("doMemberList");
		}
	}
	// 1
	@Override
	public boolean preHandle( HttpServletRequest request, 
							  HttpServletResponse response, 
							  Object handler) throws Exception {
		// TODO Auto-generated method stub
		//return HandlerInterceptor.super.preHandle(request, response, handler);
		System.out.println("SampleInterceptor.preHandle Start");
		HandlerMethod method = (HandlerMethod) handler;
		Method methodObj = method.getMethod();
		System.out.println("Bean: " + method.getBean());
		System.out.println("Method: " + methodObj);
		
		return true;
	}
}
