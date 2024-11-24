package com.blog.userCrud.exceptions;

//This exception serves as a generic exception for any unexpected errors or business logic violations that occur within the service layer.
public class ServiceLogicException extends Exception {
	public ServiceLogicException() {
		super("Something went wrong. Please try again later!");
	}
}