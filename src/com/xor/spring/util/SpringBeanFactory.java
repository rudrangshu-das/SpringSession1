package com.xor.spring.util;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.xor.spring.core.EmployeeRepository;

public class SpringBeanFactory implements ApplicationContextAware{

	private final Logger log = Logger.getLogger(EmployeeRepository.class);

	private static ApplicationContext ctx;

	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		ctx = context;
		log.info("----setApplicationContext called ----");
	}

	public static Object getBean(String name) {
		return ctx.getBean(name);
	}

}
