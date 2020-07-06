package com.appinventiv.springbootproject.appinventivspringbootapplication.exception;

import java.util.Date;
import java.util.Set;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.WebUtils;

/*
 * This is the class to handle various custom exceptions .
 * We have used @ControllerAdvice , so that we can used exceptionObjects across multiple
 * Rest Controllers
 */
@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) 
			throws Exception {
		
		 ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), "Internal server error");
		 
		return new ResponseEntity(exceptionResponse,HttpStatus.INTERNAL_SERVER_ERROR); 	
		 
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<Object> handleUserNotFoundException(Exception ex, WebRequest request) 
			throws Exception {
		
		 ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), "details are not available in request parameter");
	
		return new ResponseEntity(exceptionResponse,HttpStatus.NOT_FOUND); 	
		 
	}
	
	@Override
	public ResponseEntity<Object> handleTypeMismatch(
			TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		 ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), "There is type mismatch in the parameter ");
			
			return new ResponseEntity(exceptionResponse,HttpStatus.NOT_FOUND); 	
	}

	
	
	@Override
	public ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),ex.getBindingResult().toString());
		return new ResponseEntity(exceptionResponse,HttpStatus.BAD_REQUEST); 	
	}
	
	@Override
	public ResponseEntity<Object> handleHttpRequestMethodNotSupported(
			HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		pageNotFoundLogger.warn(ex.getMessage());

		Set<HttpMethod> supportedMethods = ex.getSupportedHttpMethods();
		if (!CollectionUtils.isEmpty(supportedMethods)) {
			headers.setAllow(supportedMethods);
		}
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),"Method not allowed");
		return new ResponseEntity(exceptionResponse,HttpStatus.METHOD_NOT_ALLOWED); 	
	}
	
	@Override
	public ResponseEntity<Object> handleMissingPathVariable(
			MissingPathVariableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),"Request Parameter missed ");
		return new ResponseEntity(exceptionResponse,HttpStatus.BAD_REQUEST); 	
	}

	
	
}
