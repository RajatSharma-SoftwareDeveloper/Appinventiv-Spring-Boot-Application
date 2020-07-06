package com.appinventiv.springbootproject.appinventivspringbootapplication.junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.appinventiv.springbootproject.appinventivspringbootapplication.AppinventivSpringBootApplication;
import com.appinventiv.springbootproject.appinventivspringbootapplication.controller.RestApiCrudController;
import com.appinventiv.springbootproject.appinventivspringbootapplication.dto.UserDetail;
import com.appinventiv.springbootproject.appinventivspringbootapplication.exception.UserNotFoundException;
import com.appinventiv.springbootproject.appinventivspringbootapplication.service.UserRepositoryService;

/**
 * This class is used to test RestApiWithCrudControllerTestCases 
 * @author Rajat
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppinventivSpringBootApplication.class)
public class RestApiCrudControllerTest {
	
		private Logger  logger = LoggerFactory.getLogger(this.getClass());
		
		@Autowired
		RestApiCrudController controller;
		
		@Autowired
		UserRepositoryService service;
		
		@Test
		public void listOfAllMoviesNotNull() {
			
			ResponseEntity<List<String>> listOfAllMovie = controller.getListOfAllMovie();
			List<String> moviesList = listOfAllMovie.getBody();
			logger.info("list of Movies {}",moviesList);
			
			assertNotNull(moviesList);
			
		}
		
		@Test
		public void listOfAllMoviesContainsMovie() {
			
			ResponseEntity<List<String>> listOfAllMovie = controller.getListOfAllMovie();
			List<String> moviesList = listOfAllMovie.getBody();
			logger.info("list of Movies {}",moviesList);
			
			assertEquals(true, moviesList.contains("Battleship"));
		}
		
		@Test
		public void listOfAllMoviesCheckSize() {
			
			ResponseEntity<List<String>> listOfAllMovie = controller.getListOfAllMovie();
			List<String> moviesList = listOfAllMovie.getBody();
			logger.info("list of Movies {}",moviesList);
			
			assertEquals(10, moviesList.size());
		}
		
		@Test
		public void getMovieByUserId() {
			
			 ResponseEntity<Object> response = controller.getMovieByUserId((long) 8);
			 	String movieName = response.getBody().toString();
			
			 	logger.info("movieName {}",movieName);
			
			 	assertEquals("Avenger", movieName);
		}
		
		@Test
		public void getMovieByUserIdNotNull() {
			
			 ResponseEntity<Object> response = controller.getMovieByUserId((long) 8);
			 	String movieName = response.getBody().toString();
			
			 	logger.info("movieName {}",movieName);
			
			 	assertNotNull(movieName);
		}
		
		@Test
		public void updateMovieByUserIdNotNull() {
			
				ResponseEntity<Object> response = controller.updateMovieByUserId((long) 88, "sholay");
			 	UserDetail userDetail = (UserDetail) response.getBody();
			 	logger.info("userDetail is {}",userDetail);
			 	assertNotNull(userDetail);
		}
		
		@Test
		public void updateMovieByUserIdCheckTitle() {
			
				ResponseEntity<Object> response = controller.updateMovieByUserId((long) 88, "sholay");
			 	UserDetail userDetail = (UserDetail) response.getBody();
			 	logger.info("userDetail is {}",userDetail);
			 	assertNotEquals("Battleship", userDetail.getTitle());
		}
		
		@Test
		public void updateMovieByUserIdCheckOtherInformation() {
			
				ResponseEntity<Object> response = controller.updateMovieByUserId((long) 88, "sholay");
			 	UserDetail userDetail = (UserDetail) response.getBody();
			 	logger.info("userDetail is {}",userDetail);
			 	assertNotEquals("Battleship", userDetail.getTitle());
			 	ResponseEntity<Object> response1 = controller.getMovieByUserId((long) 88);
				String movieName = response1.getBody().toString();
				
			 	assertNotEquals("Battleship", movieName);
		}
		
//		@Test
//		public void deleteMovieByUserIdNotNull() {
//			
//				ResponseEntity<Object> userDetail = controller.deleteMovieByUserId((long) 100);
//			 	logger.info("userDetail is {}",userDetail);
//			 	assertNotNull(userDetail);
//		}
		
		@Test
		public void userCanAddMovieNotNull() {
				
				UserDetail userDetail = new UserDetail();
				userDetail.setUserId((long) 4);
				userDetail.setUsername("Varun");
				userDetail.setTitle("Battleship");
				userDetail.setCategory("Hollywood");
				userDetail.setRating(5);
				
				ResponseEntity<Object> userCanAddMovie = controller.userCanAddMovie(userDetail);	
			 	logger.info("userCanAddMovieNotNull is {}",userCanAddMovie);
			 	assertNotNull(userCanAddMovie);
		}
		
		@Test
		public void userCanAddMovieAddedToDatabase() {
				
				UserDetail userDetail = new UserDetail();
				userDetail.setUserId((long) 4);
				userDetail.setUsername("Varun");
				userDetail.setTitle("Battleship");
				userDetail.setCategory("Hollywood");
				userDetail.setRating(5);
				
				ResponseEntity<Object> userCanAddMovie = controller.userCanAddMovie(userDetail);	
			 	logger.info("userCanAddMovieAddedToDatabase is {}",userCanAddMovie);
			 	 int statusCodeValue = userCanAddMovie.getStatusCodeValue();
			 	
			 	assertEquals(201,statusCodeValue);
			 		
		}
		
		@Test()
		public void userCanAddMovieNotAddedToDatabaseForTitle() {
				
				try
				{
				UserDetail userDetail = new UserDetail();
				userDetail.setUserId((long) 4);
				userDetail.setUsername("Varun");
				userDetail.setTitle("B");
				userDetail.setCategory("Hollywood");
				userDetail.setRating(5);
				
				ResponseEntity<Object> userCanAddMovie = controller.userCanAddMovie(userDetail);	
			 	logger.info("userCanAddMovieNotAddedToDatabaseForTitle is {}",userCanAddMovie);
				}
				catch(UserNotFoundException e)
				{
					assertEquals("movie title size should be minimum 2 and max should be 25", e.getErrorMessage());
				}
			 		
		}
		
		

		@Test
		public void userCanAddMovieNotAddedToDatabaseForCategory() {
				
			try
			{
				UserDetail userDetail = new UserDetail();
				userDetail.setUserId((long) 4);
				userDetail.setUsername("Varun");
				userDetail.setTitle("BBB");
				userDetail.setCategory("H");
				userDetail.setRating(5);
				
				ResponseEntity<Object> userCanAddMovie = controller.userCanAddMovie(userDetail);	
			 	logger.info("userCanAddMovieNotAddedToDatabaseForCategory is {}",userCanAddMovie);
			 	HttpStatus statusCode = userCanAddMovie.getStatusCode();
			}
			catch(UserNotFoundException e)
			{
				assertEquals("movie category size should be minimum 2 and max should be 25", e.getErrorMessage());

			}
			 		
		}
		
		
		@Test
		public void userCanAddMovieNotAddedToDatabaseForUserName() {
				
			try
			{
				UserDetail userDetail = new UserDetail();
				userDetail.setUserId((long) 4);
				userDetail.setUsername("V");
				userDetail.setTitle("BBB");
				userDetail.setCategory("Hollywood");
				userDetail.setRating(50);
				
				ResponseEntity<Object> userCanAddMovie = controller.userCanAddMovie(userDetail);	
			 	logger.info("userCanAddMovieNotAddedToDatabaseForUserName is {}",userCanAddMovie);
			 	HttpStatus statusCode = userCanAddMovie.getStatusCode();
			}
			catch(UserNotFoundException e)
			{
				assertEquals("UserName needs to have minimum 2 and maximum 20 character", e.getErrorMessage());
			}
			 		
		}
		
		@Test
		public void userCanAddMovieNotAddedToDatabaseForRating() {
			try
			{
				
				UserDetail userDetail = new UserDetail();
				userDetail.setUserId((long) 4);
				userDetail.setUsername("Varun");
				userDetail.setTitle("BBBB");
				userDetail.setCategory("Hollywood");
				userDetail.setRating(50);
				
				ResponseEntity<Object> userCanAddMovie = controller.userCanAddMovie(userDetail);	
			 	logger.info("userCanAddMovieNotAddedToDatabaseForRating is {}",userCanAddMovie);
			 	Object body = userCanAddMovie.getBody();
			}
			catch(UserNotFoundException e)
			{
				assertEquals("Allowed movie rating is between 0.5 and 5 ", e.getErrorMessage());

			}
		}
		
}
