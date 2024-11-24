package com.blog.userCrud.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.blog.userCrud.dtos.ApiResponseDto;
import com.blog.userCrud.dtos.GamesDetailsRequestDto;
import com.blog.userCrud.dtos.SummaryRequestDto;
import com.blog.userCrud.dtos.SupportRequestDto;
import com.blog.userCrud.exceptions.GameAlreadyExistsException;
import com.blog.userCrud.exceptions.GameNotFoundException;
import com.blog.userCrud.exceptions.QaAlreadyExistsException;
import com.blog.userCrud.exceptions.QaNotFoundException;
import com.blog.userCrud.exceptions.ServiceLogicException;
import com.blog.userCrud.services.GameService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("/games")
public class GameController {

	@Autowired
	public GameService gameService;

	@PostMapping(value="/newgame", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<ApiResponseDto<?>> addGame(
			@RequestPart("gameDetails") GamesDetailsRequestDto gamesDetailsRequestDto)
			throws GameAlreadyExistsException, ServiceLogicException {
		return gameService.addNewGame(gamesDetailsRequestDto);
	}

	@GetMapping("/get/allgames")
	public ResponseEntity<ApiResponseDto<?>> getAllGames() throws ServiceLogicException {
		return gameService.getAllGames();
	}

	@GetMapping("/get/game/{id}")
	public ResponseEntity<ApiResponseDto<?>> getAGame(@PathVariable String id)
			throws GameNotFoundException, ServiceLogicException {
		return gameService.getGameById(id);
	}

	@PutMapping(value="/updategame/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<ApiResponseDto<?>> updateGame(
			 GamesDetailsRequestDto gameDetailsRequestDto, 
			@PathVariable String id)
			throws GameNotFoundException, ServiceLogicException {
		return gameService.updateGame(gameDetailsRequestDto, id);
	}

	@DeleteMapping("/deletegame/{id}")
	public ResponseEntity<ApiResponseDto<?>> deleteGame(@PathVariable String id)
			throws GameNotFoundException, ServiceLogicException {
		return gameService.deleteGame(id);
	}

	@GetMapping("/getsummary")
	public ResponseEntity<ApiResponseDto<?>> getSummary() throws ServiceLogicException {
		return gameService.getGameSummary();
	}

	@PutMapping("/updatesummary")
	public ResponseEntity<ApiResponseDto<?>> updateSummary(@Valid @RequestBody SummaryRequestDto summaryRequestDto)
			throws ServiceLogicException {
		return gameService.updateGameSummary(summaryRequestDto);
	}

	@PostMapping("/newqa")
	public ResponseEntity<ApiResponseDto<?>> addQA(@Valid @RequestBody SupportRequestDto supportRequestDto)
			throws QaAlreadyExistsException, ServiceLogicException {
		return gameService.addNewQA(supportRequestDto);
	}

	@GetMapping("/get/allqa")
	public ResponseEntity<ApiResponseDto<?>> getAllQA() throws ServiceLogicException {
		return gameService.getAllQA();
	}

	@PutMapping("/updateqa/{id}")
	public ResponseEntity<ApiResponseDto<?>> updateQA(@Valid @RequestBody SupportRequestDto supportRequestDto,
			@PathVariable String id) throws QaNotFoundException, ServiceLogicException {
		return gameService.updateQA(supportRequestDto, id);
	}

	@DeleteMapping("/deleteqa/{id}")
	public ResponseEntity<ApiResponseDto<?>> deleteQA(@PathVariable String id)
			throws QaNotFoundException, ServiceLogicException {
		return gameService.deleteQA(id);
	}
}
