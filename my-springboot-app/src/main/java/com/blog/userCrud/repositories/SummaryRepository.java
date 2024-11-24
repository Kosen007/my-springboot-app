package com.blog.userCrud.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.blog.userCrud.modals.Summary;

@Repository
public interface SummaryRepository extends MongoRepository<Summary, Integer> {

	Summary findTop1ByOrderByIdAsc();

}
