package com.xor.spring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Bad Request")
public class MyCustomException extends Exception {

	public MyCustomException(String msg, Exception e) {
		super(msg, e);
	}
}
