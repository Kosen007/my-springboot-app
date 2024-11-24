package com.blog.userCrud.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.blog.userCrud.modals.User;

@Repository
public interface UserRepository extends MongoRepository<User, Integer> {

	User findByName(String name);
	
	User findById(String id);
	
	List<User> findAllByOrderByRegDateTimeDesc();
	
	User findByNameAndPassword(String name, String password);
}
