package com.blog.userCrud.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.blog.userCrud.modals.Games;

@Repository
public interface GameRepository extends MongoRepository<Games, Integer> {

	Games findGamesById(String id);
	
	Games findGamesByName(String name);
	
	List<Games> findAllGamesByOrderByRegDateTimeDesc();
}
