package com.blog.userCrud.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.blog.userCrud.dtos.ApiResponseDto;
import com.blog.userCrud.dtos.UserDetailsRequestDto;
import com.blog.userCrud.exceptions.UserAlreadyExistsException;
import com.blog.userCrud.exceptions.UserNotFoundException;
import com.blog.userCrud.exceptions.ServiceLogicException;

@Service
public interface UserService {

	// New User Registration
	ResponseEntity<ApiResponseDto<?>> registerUser(UserDetailsRequestDto newUserDetails)
			throws UserAlreadyExistsException, ServiceLogicException;

	// Get All Users
	ResponseEntity<ApiResponseDto<?>> getAllUsers() throws ServiceLogicException;

	// Update specific User
	ResponseEntity<ApiResponseDto<?>> updateUser(UserDetailsRequestDto newUserDetails, String id)
			throws UserNotFoundException, ServiceLogicException;

	// Delete specific User
	ResponseEntity<ApiResponseDto<?>> deleteUser(String id) throws ServiceLogicException, UserNotFoundException;

	// Login User check
	ResponseEntity<ApiResponseDto<?>> checkUser(String name, String password)
			throws UserNotFoundException, ServiceLogicException;
}
