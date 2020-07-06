package com.appinventiv.springbootproject.appinventivspringbootapplication.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.appinventiv.springbootproject.appinventivspringbootapplication.dto.UserDetail;
import com.appinventiv.springbootproject.appinventivspringbootapplication.model.Movie;
import com.appinventiv.springbootproject.appinventivspringbootapplication.model.User;

/*
 * This is the service class to interact to the database 
 * and do CRUD operations by using EntityManger .
 */
@Repository
@Transactional
public class UserRepositoryService {

	private static final Logger logger = LoggerFactory.getLogger(UserRepositoryService.class);
	
	@PersistenceContext
	EntityManager entityManager;
	
	public boolean addMovie(UserDetail userDetail)
	{
		boolean flag = false;
		
		try {
			
			Movie movie = new Movie(userDetail.getTitle(),userDetail.getCategory(),userDetail.getRating());
			User user = new User(userDetail.getUsername());
			
			user.setMovie(movie);
			entityManager.persist(movie);
			
			entityManager.persist(user);
			
			flag =true;
			
			entityManager.close();
		} catch (Exception e) {
			entityManager.close();
			logger.trace("Exception addMovie is {} " ,e);
			return flag;
		}
		return flag;
	}
	
	public ArrayList<String> getListOfAllMovie()
	{
		ArrayList<String> movieList = new ArrayList<String>();		
	
		try
		{
			Query createQuery = entityManager.createNativeQuery("select * from User", User.class);
			
			List<User> resultList = createQuery.getResultList();
			
			for(User user :resultList)
			{
				if(!movieList.contains(user.getMovie().getTitle()))
					movieList.add(user.getMovie().getTitle());
			}
			
			
		}
		catch(Exception e)
		{
			logger.trace("Exception getListOfAllMovie is {} " ,e);
			return movieList;
			
		}
		
		return movieList;
	}
	
	public String getMovieById(Long userId)
	{ 
		String titleMovie=null;
		try
		{
			User user = entityManager.find(User.class, userId);
			
			titleMovie =user.getMovie().getTitle();
			
			entityManager.close();
		}
		catch(Exception e)
		{
			logger.trace("Exception getMovieById is {} " ,e);
			return titleMovie;
		}
		
		return titleMovie;
	}
	
	public boolean deleteMovie(Long userId)
	{
		boolean flag=false;
		
		try
		{
			User user = entityManager.find(User.class, userId);
			Movie movie = user.getMovie();
			entityManager.remove(movie);
			entityManager.remove(user);
			
			flag= true;
			
			entityManager.close();
		}
		catch(Exception e)
		{
			logger.trace("Exception deleteMovie is {} " ,e);
			return flag;
		}
		
	    return flag;	
	}
	
	
	public boolean checkUserIdExistOrNot(Long userId)
	{
		boolean flag = false;
		
		Query createQuery = entityManager.createNativeQuery("select * from User", User.class);
		
		List<User> resultList = createQuery.getResultList();
		
		for(User user :resultList)
		{
			if(user.getUserid() == userId)
			{
				flag=true;
				break;
			}
		}
		
		return flag;
	}
	
	public User updateMovie(Long userId, String title)
	{
		User user =null; ;
		try
		{
		    user = entityManager.find(User.class, userId);
			Movie movie = user.getMovie();
			movie.setTitle(title);
			user.setMovie(movie);
			entityManager.persist(user);
			entityManager.close();
		}
		catch(Exception e)
		{	
			logger.trace("Exception updateMovie is {} " ,e);
			return user;
		}
		
		return user;
	}
	
	
	
}
