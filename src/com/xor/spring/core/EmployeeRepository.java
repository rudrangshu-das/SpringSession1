package com.xor.spring.core;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

import com.xor.spring.event.CheckOutEvent;
import com.xor.spring.model.CheckInCheckOutLog;
import com.xor.spring.model.Employee;

public class EmployeeRepository implements ApplicationEventPublisherAware {

	private final Logger log = Logger.getLogger(EmployeeRepository.class);

	private Map<String, Employee> repo = null;
	private Map<String, CheckInCheckOutLog> checkinStatus = null;
	private Map<String, CheckInCheckOutLog> checkOutStatus = null;
	private List<Employee> activeList = null;


	public Map<String, Employee> getRepo() {
		return repo;
	}

	public Map<String, CheckInCheckOutLog> getCheckinStatus() {
		return checkinStatus;
	}

	public Map<String, CheckInCheckOutLog> getCheckOutStatus() {
		return checkOutStatus;
	}

	public List<Employee> getActiveList() {
		return activeList;
	}

	public void init() {
		log.info(" -- EmployeeRepository init called --");
		if (repo == null) {
			repo = new HashMap<String, Employee>();
		}
		if (checkinStatus == null) {
			checkinStatus = new HashMap<String, CheckInCheckOutLog>();
		}
		if (checkOutStatus == null) {
			checkOutStatus = new HashMap<String, CheckInCheckOutLog>();
		}
		if (activeList == null) {
			activeList = new ArrayList<Employee>();
		}
	}

	public void addEmployee(Employee emp) {
		repo.put("emp -" + emp.getId(), emp);
	}

	public void checkIn(int empId, CheckInCheckOutLog log) {
		if (repo.containsKey("emp -" + empId)) {
			checkinStatus.put("emp -" + empId, log);
		}
	}

	public void checkOut(int empId, CheckInCheckOutLog log) {
		if (repo.containsKey("emp -" + empId)) {
			checkOutStatus.put("emp -" + empId, log);
		}
		CheckOutEvent checkOutEvent = new CheckOutEvent(this, "emp -" + empId, new Date());
		applicationEventPublisher.publishEvent(checkOutEvent);
	}

	public void updateActiveList() {
		for (String empId : checkinStatus.keySet()) {
			if (!checkOutStatus.containsKey(empId)) {
				Employee emp = repo.get(empId);
				activeList.add(emp);
			}
		}
	}

	public enum Status {
		CHECK_IN, CHECK_OUT;
	}

	private ApplicationEventPublisher applicationEventPublisher;

	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		this.applicationEventPublisher = applicationEventPublisher;
	}
}
