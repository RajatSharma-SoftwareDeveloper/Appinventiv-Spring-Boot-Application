package com.appinventiv.springbootproject.appinventivspringbootapplication.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/*
 * This is a Movie Entity class that has movieId, movieTitle,movieCategory,movieStarRating
 * having OneToMany relationship with User class .This is the owned class, so we have 
 * used mappedBy  to map to movie .
 * 
 */
@Entity
public class Movie {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = false)
	private String title;
	
	@Column(nullable = false)
	private String category;
	
	@Column(nullable = false)
	private double rating;
	
	@OneToMany(mappedBy = "movie")
	List<User> users = new ArrayList<User>();
	
	protected Movie()
	{
		
	}
	
	public Movie(String title, String category, double starRating) {
		super();
		this.title = title;
		this.category = category;
		this.rating = starRating;
	}

	public String getTitle() {
		return title;
	}
		
	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategory() {
		return category;
	}
	
	
	
	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	@Override
	public String toString() {
		return "Movie [id=" + id + ", title=" + title + ", category=" + category + ", rating=" + rating + ", users="
				+ users + "]";
	}

	
	
}
