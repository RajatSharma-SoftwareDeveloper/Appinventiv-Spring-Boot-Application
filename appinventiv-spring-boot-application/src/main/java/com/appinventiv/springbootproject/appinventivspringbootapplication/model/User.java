package com.appinventiv.springbootproject.appinventivspringbootapplication.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/*
 * This is User entity class in which we have userName and userId 
 * and having ManyToOne relationship with Movie class .
 * This is the owning class which contains movie_id .
 * 
 * 
 */

@Entity
public class User {

	/*It contains the userId  */
	@Id
	@GeneratedValue
	private Long userid ;
	
	/*It contains the userName  */
	@Column(nullable = false)
	private String username;
	
	
	@ManyToOne
	private Movie movie;
	
	protected User()
	{
		
	}
	
	public User( String userName) {
		super();
		this.username = userName;
	}
	
	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie=movie;
	}
	

	public String getUserName() {
		return username;
	}

	public void setUserName(String userName) {
		this.username = userName;
	}
	
	
	public Long getUserid() {
		return userid;
	}

	

	@Override
	public String toString() {
		return "User [userId=" + userid + ", userName=" + username + "]";
	}
	
	
}
