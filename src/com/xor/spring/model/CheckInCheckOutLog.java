package com.xor.spring.model;

import java.util.Date;

import com.xor.spring.core.EmployeeRepository.Status;

public class CheckInCheckOutLog {

	private Date date;
	private Status status;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}
