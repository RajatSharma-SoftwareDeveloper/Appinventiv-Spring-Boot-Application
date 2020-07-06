package com.appinventiv.springbootproject.appinventivspringbootapplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.appinventiv.springbootproject.appinventivspringbootapplication.controller.RestApiCrudController;

@SpringBootApplication
public class AppinventivSpringBootApplication {
	
	public static final Logger logger = LoggerFactory.getLogger(RestApiCrudController.class);
	
	public static void main(String[] args) {
		SpringApplication.run(AppinventivSpringBootApplication.class, args);
		   logger.info("Spring Boot Application has been intialized the context and started  .");
	}
	
}
