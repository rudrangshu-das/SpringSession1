package com.xor.spring.core;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class EmployeeRepoPostProcessor implements BeanPostProcessor{

	private final Logger log = Logger.getLogger(EmployeeRepoPostProcessor.class);

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName)
			throws BeansException {
		log.info("-- EmployeeRepoPostProcessor called --");
		if(bean instanceof EmployeeRepository){
			EmployeeRepository empRepo = (EmployeeRepository)bean;
			empRepo.init();
		}
		return bean;
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName)
			throws BeansException {
		return bean;
	}

}
