package com.xor.spring.event;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;

import com.xor.spring.core.EmployeeRepository;
import com.xor.spring.model.DailyTimesheet;

public class CheckOutEventListener implements ApplicationListener<CheckOutEvent> {

	@Autowired
	private EmployeeRepository employeeRepository;

	public EmployeeRepository getEmployeeRepository() {
		return employeeRepository;
	}

	@Override
	public void onApplicationEvent(CheckOutEvent event) {
		String employeeId = event.getEmployeeId();
		Date checkoutTime = event.getCheckoutTime();

		Date checkInTime = employeeRepository.getCheckinStatus().get(employeeId).getDate();
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String tsDate = df.format(checkInTime);
		
		long diff = checkoutTime.getTime() - checkInTime.getTime();
		DailyTimesheet ts = new DailyTimesheet(tsDate, diff / (1000 * 60 * 60));
		employeeRepository.addTimeSheet(employeeId, ts);
	}

}
