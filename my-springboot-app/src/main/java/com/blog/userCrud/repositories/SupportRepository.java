package com.blog.userCrud.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.blog.userCrud.modals.Support;

@Repository
public interface SupportRepository extends MongoRepository<Support, Integer> {

	Support findById(String id);
	
	Support findSupportByQuestion(String question);

	List<Support> findAllByOrderByIdDesc();
}
