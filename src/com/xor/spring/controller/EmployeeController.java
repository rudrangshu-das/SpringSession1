package com.xor.spring.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xor.spring.core.EmployeeRepository;
import com.xor.spring.core.EmployeeRepository.Status;
import com.xor.spring.exception.MyCustomException;
import com.xor.spring.model.CheckInCheckOutLog;
import com.xor.spring.model.DailyTimesheet;
import com.xor.spring.model.Employee;
import com.xor.spring.model.EmployeeDetail;
import com.xor.spring.model.EmployeeVO;
import com.xor.spring.service.EmployeeService;
import com.xor.spring.util.SpringBeanFactory;

@Controller
@RequestMapping("employee")
public class EmployeeController {

	@Autowired
	private CheckInCheckOutLog checkInCheckOutLog;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private EmployeeService employeeService;

	public CheckInCheckOutLog getCheckInCheckOutLog() {
		return checkInCheckOutLog;
	}

	public EmployeeRepository getEmployeeRepository() {
		return employeeRepository;
	}

	public EmployeeService getEmployeeService() {
		return employeeService;
	}

	@RequestMapping("getById/{empId}")
	@ResponseBody
	public Employee getById(@PathVariable int empId) {
		return employeeRepository.getRepo().get("emp -" + empId);
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public String add(@RequestBody EmployeeVO vo) throws MyCustomException {

		if (employeeRepository.getRepo().containsKey("emp -" + vo.getId())) {
			throw new MyCustomException("Employee already exists");
		}
		Employee employee = new Employee(vo);
		EmployeeDetail employeeDetail = (EmployeeDetail) SpringBeanFactory.getBean(vo.getEmpDetailBeanId());
		employee.setDetail(employeeDetail);
		employeeRepository.addEmployee(employee);
		return "Success";
	}

	@RequestMapping("checkIn/{empId}")
	@ResponseBody
	public String checkIn(@PathVariable int empId) {
		checkInCheckOutLog.setDate(new Date(System.currentTimeMillis()));
		checkInCheckOutLog.setStatus(Status.CHECK_IN);

		employeeRepository.checkIn(empId, checkInCheckOutLog);
		return "Success";
	}

	@RequestMapping("checkOut/{empId}")
	@ResponseBody
	public String checkOut(@PathVariable int empId) {
		checkInCheckOutLog.setDate(new Date(System.currentTimeMillis()));
		checkInCheckOutLog.setStatus(Status.CHECK_OUT);

		employeeRepository.checkOut(empId, checkInCheckOutLog);
		return "Success";
	}

	@RequestMapping("timesheets/{empId}")
	@ResponseBody
	public List<DailyTimesheet> getEmployeeTimesheets(@PathVariable int empId) {
		return employeeService.getTimesheets(empId);
	}

	@RequestMapping("getActiveEmployees")
	@ResponseBody
	public List<Employee> getActiveEmployees() {
		return employeeRepository.getActiveList();
	}
}
