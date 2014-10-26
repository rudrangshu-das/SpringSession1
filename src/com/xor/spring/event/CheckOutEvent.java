package com.xor.spring.event;

import java.util.Date;

import org.springframework.context.ApplicationEvent;

public class CheckOutEvent extends ApplicationEvent {

	private final String employeeId;
	private final Date checkoutTime;

	public CheckOutEvent(Object source, String employeeId, Date checkoutTime) {
		super(source);
		this.employeeId = employeeId;
		this.checkoutTime = checkoutTime;
	}

	public Date getCheckoutTime() {
		return checkoutTime;
	}

	public String getEmployeeId() {
		return employeeId;
	}

}
