package com.xor.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xor.spring.core.AppConfig;

@Controller
@RequestMapping("appConfig")
public class AppConfigController {

	@Autowired
	private AppConfig appConfig;

	@RequestMapping(value = "/set/timeZone/{timeZone}", method = RequestMethod.POST)
	@ResponseBody
	public String setTimeZone(@PathVariable String timeZone) {

		appConfig.setTimeZone(timeZone);
		return "Success";
	}
	
	@RequestMapping("getTimeZone")
	@ResponseBody
	public String getTimeZone() {
		return appConfig.getTimeZone();
	}
}
