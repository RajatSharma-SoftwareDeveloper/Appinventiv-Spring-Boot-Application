package com.appinventiv.springbootproject.appinventivspringbootapplication.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.appinventiv.springbootproject.appinventivspringbootapplication.dto.UserDetail;
import com.appinventiv.springbootproject.appinventivspringbootapplication.exception.CustomErrorMessage;
import com.appinventiv.springbootproject.appinventivspringbootapplication.exception.UserNotFoundException;

/*
 * This is the validation class to do validation for Post request when user create a user and adds to movie .
 */
public class ValidateUserDetails {

	
	public static  boolean validateUserDetails(UserDetail userDetail, ResponseEntity<Object> response)
	{
		boolean validateFlag = true ;
		
			String userName = userDetail.getUsername();
			String title = userDetail.getTitle();
			String category = userDetail.getCategory();
			double rating = userDetail.getRating();
			
			if(userName == null || ((userName.length() <= 2) || (userName.length() >20)))
			{
				validateFlag=false;
				response = new ResponseEntity<>("UserName needs to have minimum 2 and maximum 20 character",HttpStatus.NOT_FOUND);
				throw new UserNotFoundException("UserName needs to have minimum 2 and maximum 20 character");
				
			}
			else if(title ==null || ((title.length() <= 2) || (title.length() > 25)))
			{
				validateFlag=false;
				response = new ResponseEntity<>("movie title size should be minimum 2 and max should be 25",HttpStatus.NOT_FOUND);
				throw new UserNotFoundException("movie title size should be minimum 2 and max should be 25");
			}
			else if(category ==null || ((category.length() <= 2) || (category.length() >25)))
			{
				validateFlag=false;
				response = new ResponseEntity<>("movie category size should be minimum 2 and max should be 25",HttpStatus.NOT_FOUND);
				throw new UserNotFoundException("movie category size should be minimum 2 and max should be 25");
			}
			else if ( rating == Double.MIN_VALUE|| rating <0 || Double.isNaN(rating) || Double.isInfinite(rating)|| rating <0.5 || rating >5)
			{
				validateFlag=false;
				response = new ResponseEntity<>("Allowed movie rating is between 0.5 and 5 ",HttpStatus.NOT_FOUND);
				throw new UserNotFoundException("Allowed movie rating is between 0.5 and 5 ");
			}
			
			return validateFlag;
		
		
	}
	
}
