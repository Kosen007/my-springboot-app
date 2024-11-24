package com.blog.userCrud.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.blog.userCrud.dtos.ApiResponseDto;
import com.blog.userCrud.dtos.GamesDetailsRequestDto;
import com.blog.userCrud.dtos.SummaryRequestDto;
import com.blog.userCrud.dtos.SupportRequestDto;
import com.blog.userCrud.exceptions.GameAlreadyExistsException;
import com.blog.userCrud.exceptions.GameNotFoundException;
import com.blog.userCrud.exceptions.QaAlreadyExistsException;
import com.blog.userCrud.exceptions.QaNotFoundException;
import com.blog.userCrud.exceptions.ServiceLogicException;

@Service
public interface GameService {

	// Add a New Game
	ResponseEntity<ApiResponseDto<?>> addNewGame(GamesDetailsRequestDto newGameDetails)
			throws GameAlreadyExistsException, ServiceLogicException;

	// Get all Games
	ResponseEntity<ApiResponseDto<?>> getAllGames() throws ServiceLogicException;

	// Get a Game
	ResponseEntity<ApiResponseDto<?>> getGameById(String id) throws GameNotFoundException, ServiceLogicException;

	// Update specific Game
	ResponseEntity<ApiResponseDto<?>> updateGame(GamesDetailsRequestDto newGameDetails, String id)
			throws GameNotFoundException, ServiceLogicException;

	// Delete specific Game
	ResponseEntity<ApiResponseDto<?>> deleteGame(String id) throws ServiceLogicException, GameNotFoundException;

	// Get Game Summary Information
	ResponseEntity<ApiResponseDto<?>> getGameSummary() throws ServiceLogicException;

	// UPdate Game Summary Information
	ResponseEntity<ApiResponseDto<?>> updateGameSummary(SummaryRequestDto summaryRequestDto)
			throws ServiceLogicException;

	// Get All QA
	ResponseEntity<ApiResponseDto<?>> getAllQA() throws ServiceLogicException;

	// Add a New QA
	ResponseEntity<ApiResponseDto<?>> addNewQA(SupportRequestDto newQa)
			throws QaAlreadyExistsException, ServiceLogicException;

	// Update QA
	ResponseEntity<ApiResponseDto<?>> updateQA(SupportRequestDto newQa, String id)
			throws QaNotFoundException, ServiceLogicException;

	// Delete QA
	ResponseEntity<ApiResponseDto<?>> deleteQA(String id) throws ServiceLogicException, QaNotFoundException;

}