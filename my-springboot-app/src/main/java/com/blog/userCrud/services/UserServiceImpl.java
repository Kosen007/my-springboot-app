package com.blog.userCrud.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.blog.userCrud.dtos.ApiResponseDto;
import com.blog.userCrud.dtos.ApiResponseStatus;
import com.blog.userCrud.dtos.UserDetailsRequestDto;
import com.blog.userCrud.exceptions.ServiceLogicException;
import com.blog.userCrud.exceptions.UserAlreadyExistsException;
import com.blog.userCrud.exceptions.UserNotFoundException;
import com.blog.userCrud.modals.User;
import com.blog.userCrud.repositories.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class UserServiceImpl implements UserService {

	@Autowired(required=true)
	private UserRepository userRepository;

	@Override
	public ResponseEntity<ApiResponseDto<?>> registerUser(UserDetailsRequestDto newUserDetails)
			throws UserAlreadyExistsException, ServiceLogicException {
		try {
			if (userRepository.findByName(newUserDetails.getName()) != null) {
				throw new UserAlreadyExistsException(
						"Registration failed: User already exists with name " + newUserDetails.getName());
			}

			User newUser = new User(newUserDetails.getName(), newUserDetails.getPassword(), LocalDateTime.now());

			// save() is an in built method given by JpaRepository
			userRepository.save(newUser);

			return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponseDto<>(ApiResponseStatus.SUCCESS.name(),
					"New user account has been successfully created!"));

		} catch (UserAlreadyExistsException e) {
//			throw new UserAlreadyExistsException(e.getMessage());
			log.error("Failed to create new user account: " + e.getMessage());
			return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponseDto<>(ApiResponseStatus.FAIL.name(),
					"Failed to create new user account: " + e.getMessage()));
		} catch (Exception e) {
			log.error("Failed to create new user account: " + e.getMessage());
//			throw new ServiceLogicException();
			return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponseDto<>(ApiResponseStatus.FAIL.name(),
					"Failed to create new user account: " + e.getMessage()));
		}
	}

	@Override
	public ResponseEntity<ApiResponseDto<?>> getAllUsers() throws ServiceLogicException {
		try {
			List<User> users = userRepository.findAllByOrderByRegDateTimeDesc();

			return ResponseEntity.status(HttpStatus.OK)
					.body(new ApiResponseDto<>(ApiResponseStatus.SUCCESS.name(), users));

		} catch (Exception e) {
			log.error("Failed to fetch all users: " + e.getMessage());
			throw new ServiceLogicException();
		}
	}

	@Override
	public ResponseEntity<ApiResponseDto<?>> updateUser(UserDetailsRequestDto newUserDetails, String id)
			throws UserNotFoundException, ServiceLogicException {
		try {
			User user = userRepository.findById(id);
			if(user == null) throw new UserNotFoundException("User not found with id " + id);

			user.setName(newUserDetails.getName());
			user.setPassword(newUserDetails.getPassword());

			userRepository.save(user);

			return ResponseEntity.status(HttpStatus.OK)
					.body(new ApiResponseDto<>(ApiResponseStatus.SUCCESS.name(), "User account updated successfully!"));

		} catch (UserNotFoundException e) {
			throw new UserNotFoundException(e.getMessage());
		} catch (Exception e) {
			log.error("Failed to update user account: " + e.getMessage());
			throw new ServiceLogicException();
		}
	}

	@Override
	public ResponseEntity<ApiResponseDto<?>> deleteUser(String id)
			throws ServiceLogicException, UserNotFoundException {
		try {
			User user = userRepository.findById(id);
			if(user == null) {
				throw new UserNotFoundException("User not found with id " + id);
			}

			userRepository.delete(user);

			return ResponseEntity.status(HttpStatus.OK)
					.body(new ApiResponseDto<>(ApiResponseStatus.SUCCESS.name(), "User account deleted successfully!"));
		} catch (UserNotFoundException e) {
			throw new UserNotFoundException(e.getMessage());
		} catch (Exception e) {
			log.error("Failed to delete user account: " + e.getMessage());
			throw new ServiceLogicException();
		}
	}

	@Override
	public ResponseEntity<ApiResponseDto<?>> checkUser(String name, String password) throws UserNotFoundException, ServiceLogicException {
		try {
			User user = userRepository.findByNameAndPassword(name, password);
			
			if(user == null) throw new UserNotFoundException("User not found with name " + name);
			
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ApiResponseDto<>(ApiResponseStatus.SUCCESS.name(), "User account login successfully!"));

		} catch (Exception e) {
			log.error("Failed to login: " + e.getMessage());
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ApiResponseDto<>(ApiResponseStatus.FAIL.name(), "Failed to login: " + e.getMessage()));

		}
	}

}
