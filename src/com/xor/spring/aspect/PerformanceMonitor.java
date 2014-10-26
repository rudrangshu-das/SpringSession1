package com.xor.spring.aspect;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class PerformanceMonitor {
	
	private static final transient Logger log = Logger.getLogger(PerformanceMonitor.class);
	private long startTimeInMillis = 0;
	private long endTimeInMillis = 0;

	@Before("EmployeeRepositoryPointcut.monitorPerf()")
	public void start(){
		startTimeInMillis = System.currentTimeMillis();
	}
	
	@AfterReturning("EmployeeRepositoryPointcut.monitorPerf()")
	public void logTimeElapsed(JoinPoint jp){
		endTimeInMillis = System.currentTimeMillis();
		log.info("==============performance monitor log start ================");
		Date d = new Date(endTimeInMillis);
		SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		String formatDate = df.format(d);
		log.info("Class =" + jp.getSignature().getDeclaringTypeName() + " | Method =" + jp.getSignature().getName() + " last accessed at ="
				+ formatDate);
		long elapsedTime = endTimeInMillis-startTimeInMillis;
		log.info("Execution time in millis for method " + jp.getSignature().getName() + " is " + elapsedTime);
		log.info("============================================================");
	}
	
}
