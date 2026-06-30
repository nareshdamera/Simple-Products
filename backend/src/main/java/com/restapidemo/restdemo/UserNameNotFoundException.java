package com.restapidemo.restdemo;

public class UserNameNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserNameNotFoundException(String S)
	{
		super(S);
	}

}
