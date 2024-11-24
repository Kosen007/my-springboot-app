package com.blog.userCrud.exceptions;

//This exception is thrown when attempting to create a new game, but a game with the same identifier (e.g., name) already exists in the database.
public class QaAlreadyExistsException extends Exception {
	public QaAlreadyExistsException(String message) {
		super(message);
	}
}