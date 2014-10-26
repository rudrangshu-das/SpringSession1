package com.xor.spring.model;

public class SSEBand1Compensation implements Compensation{

	private String grade;
	private String band;
	private long fixedPay;
	private long variablePay;

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getBand() {
		return band;
	}

	public void setBand(String band) {
		this.band = band;
	}

	public long getFixedPay() {
		return fixedPay;
	}

	public void setFixedPay(long fixedPay) {
		this.fixedPay = fixedPay;
	}

	public long getVariablePay() {
		return variablePay;
	}

	public void setVariablePay(long variablePay) {
		this.variablePay = variablePay;
	}


}
