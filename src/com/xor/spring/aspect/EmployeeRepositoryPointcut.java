package com.xor.spring.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class EmployeeRepositoryPointcut {

	@Pointcut("execution(* com.xor.spring.core.EmployeeRepository.*(..))")
	public void monitorPerf() {
	}
}
