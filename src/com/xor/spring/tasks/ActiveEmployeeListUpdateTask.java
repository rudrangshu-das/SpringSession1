package com.xor.spring.tasks;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import com.xor.spring.core.EmployeeRepository;

public class ActiveEmployeeListUpdateTask {

	private final Logger log = Logger.getLogger(ActiveEmployeeListUpdateTask.class);

	@Autowired
	private EmployeeRepository employeeRepository;

	public EmployeeRepository getEmployeeRepository() {
		return employeeRepository;
	}

	@Scheduled(cron = "*/30 * * * * ?")
	public void updateActiveList() {
		log.info(" -- ActiveEmployeeListUpdateTask running --");
		employeeRepository.updateActiveList();
	}
}
