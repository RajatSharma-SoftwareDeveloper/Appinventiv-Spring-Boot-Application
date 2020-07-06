package com.appinventiv.springbootproject.appinventivspringbootapplication.exception;

/*
 * This class is used to handle custom exceptions for user defined exceptions.
 */
public class UserNotFoundException extends RuntimeException{
	
	private String errorMessage;
	
	
	public UserNotFoundException(String errorMessage) {
		super(errorMessage);
		this.errorMessage=errorMessage;
	}

	
	
	public String getErrorMessage()
	{
		return errorMessage;
	}
		
}
