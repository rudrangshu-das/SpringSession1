package com.xor.spring.core;

import java.util.TimeZone;

public class AppConfig {

	private TimeZone tz;
	private String timeZone;

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
		tz = TimeZone.getTimeZone(timeZone);
	}

	public TimeZone getTz() {
		return tz;
	}

}
