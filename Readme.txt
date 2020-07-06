This is the spring boot application in which I have used SpringBoot,Hibernate,Mysql,JPA,RestFulWebServices,Docker and Junit Test Case .
This project contains DockerFile.
This project some of the JUnit Test case .
This project contains validations at various ends .
This project has handled error too .



Below are the RestApi End Points which I have Exposed 

1. GET  => 
 	   This method is used to get list of the movie name for all the User having {userId}.
	   Example Request => http://localhost:8080/getMovieByUserId/1
	 
2. POST => This method is used to add add user and associate movie to it .
	  Request Example => http://localhost:8080/userCanAddMovie
	  
	   JsonObject = {
	   "username":"Rahul",
		"title":"Jatt and Juliet",
		"category":"Pollywood",
		"rating":3
		} 

3. PUT => This method is used to update movie by passing userId and title details to update 
	  for that user .
	  
	  Request Example => http://localhost:8080/updateMovieByUserId/1/Battleship
	 

4. DELETE =>
	   This method is used to delete user details from database using userId .
	   Request Example => http://localhost:8080/deleteMovieByUserId/62


5. GET => This method is used to find the list of all the movies names .
	  Request Example =http://localhost:8080/getAllMovies
	 

