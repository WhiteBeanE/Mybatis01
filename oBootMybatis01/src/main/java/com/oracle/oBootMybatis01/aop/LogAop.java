package com.oracle.oBootMybatis01.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAop {
	
	// com.oracle.oBootMybatis01.dao 패키지 안에 있는 모든 메소드
	@Pointcut("within(com.oracle.oBootMybatis01.dao.*)")
	private void pointcutMethod() {}
	
	@Around("pointcutMethod()")
	public Object loggerAop(ProceedingJoinPoint joinPoint) throws Throwable {
		// 핵심관심사 Method 이름을 불러옴 
		String signatureStr = joinPoint.getSignature().toShortString();
		System.out.println(signatureStr + " is Start");
		// Return the current time in milliseconds
		long startTime = System.currentTimeMillis();
		
		Object obj;
		try {
			// 핵심 관심사 Method 수행 -> /interCeptor
			obj = joinPoint.proceed();
			return obj;
		} finally {
			long endtime = System.currentTimeMillis();
			System.out.println(signatureStr + " is finished");
			System.out.println(signatureStr + " 경과시간: " + (endtime - startTime));
		}
	}
}

