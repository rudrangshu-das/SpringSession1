package com.xor.spring.event;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;

import com.xor.spring.core.EmployeeRepository;
import com.xor.spring.model.DailyTimesheet;
import com.xor.spring.service.EmployeeService;

public class CheckOutEventListener implements ApplicationListener<CheckOutEvent> {

	private final Logger log = Logger.getLogger(CheckOutEventListener.class);

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private EmployeeRepository employeeRepository;

	public EmployeeService getEmployeeService() {
		return employeeService;
	}

	public EmployeeRepository getEmployeeRepository() {
		return employeeRepository;
	}

	@Override
	public void onApplicationEvent(CheckOutEvent event) {
		log.info("--- CheckOutEventListener called --");
		String employeeId = event.getEmployeeId();
		Date checkoutTime = event.getCheckoutTime();

		Date checkInTime = employeeRepository.getCheckinStatus().get(employeeId).getDate();
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String tsDate = df.format(checkInTime);

		long diff = checkoutTime.getTime() - checkInTime.getTime();
		DailyTimesheet ts = new DailyTimesheet(tsDate, diff / (1000 * 60 * 60));
		employeeService.addTimeSheet(employeeId, ts);
	}

}
