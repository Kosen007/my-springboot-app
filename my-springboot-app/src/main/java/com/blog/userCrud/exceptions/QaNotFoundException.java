package com.blog.userCrud.exceptions;

//This exception is thrown when attempting to retrieve a game from the database, but the game does not exist.
public class QaNotFoundException extends Exception {
	public QaNotFoundException(String message) {
		super(message);
	}
}