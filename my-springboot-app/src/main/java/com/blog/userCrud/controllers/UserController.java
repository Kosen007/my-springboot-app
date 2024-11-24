package com.blog.userCrud.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.userCrud.dtos.ApiResponseDto;
import com.blog.userCrud.dtos.UserDetailsRequestDto;
import com.blog.userCrud.exceptions.ServiceLogicException;
import com.blog.userCrud.exceptions.UserAlreadyExistsException;
import com.blog.userCrud.exceptions.UserNotFoundException;
import com.blog.userCrud.services.UserService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	public UserService userService;

	@PostMapping("/new")
	public ResponseEntity<ApiResponseDto<?>> registerUser(
			@Valid @RequestBody UserDetailsRequestDto userDetailsRequestDto)
			throws UserAlreadyExistsException, ServiceLogicException {
		return userService.registerUser(userDetailsRequestDto);
	}

	@GetMapping("/get/all")
	public ResponseEntity<ApiResponseDto<?>> getAllUsers() throws ServiceLogicException {
		return userService.getAllUsers();
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<ApiResponseDto<?>> updateUser(@Valid @RequestBody UserDetailsRequestDto userDetailsRequestDto,
			@PathVariable String id) throws UserNotFoundException, ServiceLogicException {
		return userService.updateUser(userDetailsRequestDto, id);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ApiResponseDto<?>> deleteUser(@PathVariable String id)
			throws UserNotFoundException, ServiceLogicException {
		return userService.deleteUser(id);
	}

	@PostMapping("/login")
	public ResponseEntity<ApiResponseDto<?>> login(@Valid @RequestBody UserDetailsRequestDto userDetailsRequestDto)
			throws UserNotFoundException, ServiceLogicException {
		return userService.checkUser(userDetailsRequestDto.getName(), userDetailsRequestDto.getPassword());
	}
}
