package com.appinventiv.springbootproject.appinventivspringbootapplication.exception;
/*
 * This class is used to handle custom exceptions for Internal Server Errors.
 */
public class CustomErrorMessage extends RuntimeException {

	private String errorMessage;
	
	public CustomErrorMessage(String errorMessage)
	{
		super(errorMessage);
		this.errorMessage =errorMessage;
	}
	
	public String getErrorMessage()
	{
		return errorMessage;
	}
	
	
}
