package com.appinventiv.springbootproject.appinventivspringbootapplication.dto;

import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

/*
 * This is the class used to display details of the user and movie class combine.
 * It has various fields 
 * @userId,@userName,@title,@category,@rating details 
 * 
 */
@Component
public class UserDetail {

	
	private Long userId;
	
	@Size(min = 2 ,message = "Not valid")
	private String username;
	
	private String title;	
	
	private String category;
	
	private double rating;
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
	public void setCategory(String category) {
		this.category = category;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	@Override
	public String toString() {
		return "UserDetail [userId=" + userId + ", username=" + username + ", title=" + title + ", category=" + category
				+ ", rating=" + rating + "]";
	}
	
	
}
