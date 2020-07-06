package com.appinventiv.springbootproject.appinventivspringbootapplication.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.appinventiv.springbootproject.appinventivspringbootapplication.dto.UserDetail;
import com.appinventiv.springbootproject.appinventivspringbootapplication.exception.CustomErrorMessage;
import com.appinventiv.springbootproject.appinventivspringbootapplication.exception.UserNotFoundException;
import com.appinventiv.springbootproject.appinventivspringbootapplication.model.User;
import com.appinventiv.springbootproject.appinventivspringbootapplication.service.UserRepositoryService;
import com.appinventiv.springbootproject.appinventivspringbootapplication.util.ValidateUserDetails;

/*
 * This is the ControllerClass that is used to do crud operations .
 */
@RestController
public class RestApiCrudController {

	public static final Logger logger = LoggerFactory.getLogger(RestApiCrudController.class);
	 
	@Autowired
	UserRepositoryService userRepository;
	
	
	/*
	 * This method is used to find the list of all the movies names .
	 * 
	 * Request Example =http://localhost:8080/getAllMovies
	 */
	@GetMapping("/getAllMovies")
	public ResponseEntity<List<String>> getListOfAllMovie()
	{
		logger.info("getAllMovies method called ");
		
		ResponseEntity<List<String>> response =null;
		ArrayList<String> movieList = null;
		
			 movieList = userRepository.getListOfAllMovie();
			
			 if(movieList == null && movieList.size() <0) {
				 logger.error("There is no list of movies available in database .");
				throw new CustomErrorMessage("There is no list of movies available in database .");	 
				
			 }
			 else
			 {
				 response = new ResponseEntity<>(movieList,HttpStatus.OK);
				 logger.info("getAllMovies executed successfully .");
			 }
		
		logger.info("List of movies {} " ,movieList);	 
		
		return response;
	}
	
	/*
	 * This method is used to add add user and associate movie to it .
	 * Request Example => http://localhost:8080/userCanAddMovie
	 * 
	 *  JsonObject = {
	 *  "username":"Rahul",
		"title":"Jatt and Juliet",
		"category":"Pollywood",
		"rating":3
	 *  
	 *  
	 *  }
	 */
	@PostMapping("/userCanAddMovie")
	public ResponseEntity<Object> userCanAddMovie(@Validated @RequestBody UserDetail userDetail)
	{ 
		logger.info("userCanAddMovie method called ");
		ResponseEntity<Object> response=null;
			
		boolean validateFlag = ValidateUserDetails.validateUserDetails(userDetail,response);
		
		if(validateFlag)
		{
		boolean saveFlag =userRepository.addMovie(userDetail);
		
		if(saveFlag)
		{
			response = new ResponseEntity<>("user created successfully and movie is added to the user ",HttpStatus.CREATED);
		}
		else
		{
		logger.error("userDetail {}" +userDetail + "are not saved in the database or due to internal error");	
		throw new CustomErrorMessage("userDetail =" +userDetail + "are not saved in the database or due to internal error");
		}
		}
		
		
		logger.info("userCanAddMovie details {} " ,response);
		
		return response;
	}
	
	
	/*
	 * This method is used to get list of the movie name for all the User having {userId}.
	 * 
	 * 
	 *  Example Request => http://localhost:8080/getMovieByUserId/1
	 */
	@GetMapping("/getMovieByUserId/{userId}")
	public ResponseEntity<Object> getMovieByUserId( @PathVariable Long userId) 
	{		
			logger.info("getMovieByUserId method called ");
			ResponseEntity<Object> response = null;
			
			String movieTitle = null;
			if(userId < 0 )
			{
				throw new UserNotFoundException("User need to send valid userId to get movie Name ");
			}			
			else
			{
		    movieTitle = userRepository.getMovieById(userId);
			
			if(movieTitle ==null)
			{
			logger.error("User not found for userID {} " +userId);		
			throw new CustomErrorMessage("User not found for userID=" +userId);
			}
			response = new ResponseEntity<>(movieTitle,HttpStatus.OK);
			}
			
			logger.info("getMovieByUserId details {} " ,response);
			return response;
	}
	
	/*
	 * This method is used to update movie by passing userId and title details to update 
	 * for that user .
	 * 
	 * Request Example => http://localhost:8080/updateMovieByUserId/1/Battleship
	 */
	@PutMapping("/updateMovieByUserId/{userId}/{title}")
	public ResponseEntity<Object> updateMovieByUserId(@PathVariable Long userId , @PathVariable String title )
	{
			logger.info("updateMovieByUserId method called ");
			ResponseEntity<Object> response = null;
			UserDetail userDetail= new UserDetail();
		
			if(userId < 0)
			{
				logger.error("User need to send valid userId to do update operation {}",userId);		

				throw new UserNotFoundException("User need to send valid userId to do update operation");
			}
			else if(title == null)
			{
				logger.error("User need to send valid title to update operation {}" , title);		

				throw new UserNotFoundException("User need to send valid title to update operation");
			}
			else
			{
			
			User user = userRepository.updateMovie(userId,title);

			if(user!=null)
			{
			userDetail.setUserId(userId);
			userDetail.setCategory(user.getMovie().getCategory());
			userDetail.setRating(user.getMovie().getRating());
			userDetail.setTitle(user.getMovie().getTitle());
			userDetail.setUsername(user.getUserName());
			response = new ResponseEntity<>(userDetail,HttpStatus.OK);
			}
			else
			{	
				logger.error("user id {}" +userId  + " does not exist , so not able to do update operation or may be due to internal server error");		

				throw new CustomErrorMessage("user id =" +userId  + " does not exist , so not able to do update operation or may be due to internal server error");
			}
			}
		
			logger.info("updateMovieByUserId details {} " ,response);

		return response;
	}
	
	/*
	 * This method is used to delete user details from database using userId .
	 * Request Example => http://localhost:8080/deleteMovieByUserId/62
	 */
	@DeleteMapping("/deleteMovieByUserId/{userId}")
	public ResponseEntity<Object> deleteMovieByUserId(@PathVariable Long userId)
	{	
		logger.info("deleteMovieByUserId method called ");
		ResponseEntity<Object> response = null;
		
		if(userId < 0)
		{					
			logger.error("User need to send valid userId to do delete operation {} ",userId);		
			throw new UserNotFoundException("User need to send valid userId to do delete operation");
		}
		else
		{
		boolean deleteFlag =userRepository.deleteMovie( userId);
		
		if(deleteFlag)
		{
		response = new ResponseEntity<>("User has been delete succesfully",HttpStatus.OK);
		}
		else
		{
		logger.error("user id {}" +userId + " does not exist in database , so not able to do delete operation or may be due to internal server error");			
		throw new CustomErrorMessage("user id =" +userId + " does not exist in database , so not able to do delete operation or may be due to internal server error");
		}
		}
		
		logger.info("deleteMovieByUserId details {} " ,response);
		return response;
	}
	
}
