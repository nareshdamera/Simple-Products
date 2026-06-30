package com.restapidemo.restdemo.Exceptions;

public class UserDefinedException extends RuntimeException {
	public UserDefinedException(String msg) {
		super(msg);
	}

}
