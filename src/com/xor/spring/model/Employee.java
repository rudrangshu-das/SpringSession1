package com.xor.spring.model;


public class Employee {

	private int id;
	private String name;
	private EmployeeDetail detail;

	public Employee() {
	}

	public Employee(EmployeeVO vo) {
		this.id = vo.getId();
		this.name = vo.getName();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public EmployeeDetail getDetail() {
		return detail;
	}

	public void setDetail(EmployeeDetail detail) {
		this.detail = detail;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", detail=" + detail + "]";
	}

}
