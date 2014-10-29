package com.xor.spring.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.xor.spring.model.DailyTimesheet;

public class EmployeeService {

	private final Logger log = Logger.getLogger(EmployeeService.class);

	private final Map<String, List<DailyTimesheet>> employeeTimesheets = new HashMap<String, List<DailyTimesheet>>();

	public void addTimeSheet(String empId, DailyTimesheet ts) {
		log.info("-- adding timesheet for emp = emp-" + empId + " ts =" + ts);
		if (employeeTimesheets.containsKey(empId)) {
			List<DailyTimesheet> list = employeeTimesheets.get(empId);
			list.add(ts);
		} else {
			List<DailyTimesheet> list = new ArrayList<DailyTimesheet>();
			employeeTimesheets.put(empId, list);
			list.add(ts);
		}
	}

	public List<DailyTimesheet> getTimesheets(int empId) {
		return employeeTimesheets.get("emp -" + empId);
	}
}
