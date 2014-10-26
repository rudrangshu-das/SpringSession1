package com.xor.spring.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringBeanFactory implements ApplicationContextAware{

	private static ApplicationContext ctx;

	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		ctx = context;
		System.out.println("----setApplicationContext called ----");
	}

	public static Object getBean(String name) {
		return ctx.getBean(name);
	}

}
