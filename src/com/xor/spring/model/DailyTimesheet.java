package com.xor.spring.model;

public class DailyTimesheet {

	private final String date;
	private final float hours;

	public DailyTimesheet(String date, float hours) {
		super();
		this.date = date;
		this.hours = hours;
	}

	public String getDate() {
		return date;
	}

	public float getHours() {
		return hours;
	}

	@Override
	public String toString() {
		return "DailyTimesheet [date=" + date + ", hours=" + hours + "]";
	}

}
