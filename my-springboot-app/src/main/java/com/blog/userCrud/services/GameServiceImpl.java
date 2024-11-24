package com.blog.userCrud.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.blog.userCrud.dtos.ApiResponseDto;
import com.blog.userCrud.dtos.ApiResponseStatus;
import com.blog.userCrud.dtos.GamesDetailsRequestDto;
import com.blog.userCrud.dtos.SummaryRequestDto;
import com.blog.userCrud.dtos.SupportRequestDto;
import com.blog.userCrud.exceptions.GameAlreadyExistsException;
import com.blog.userCrud.exceptions.GameNotFoundException;
import com.blog.userCrud.exceptions.QaAlreadyExistsException;
import com.blog.userCrud.exceptions.QaNotFoundException;
import com.blog.userCrud.exceptions.ServiceLogicException;
import com.blog.userCrud.modals.Games;
import com.blog.userCrud.modals.Summary;
import com.blog.userCrud.modals.Support;
import com.blog.userCrud.repositories.GameRepository;
import com.blog.userCrud.repositories.SummaryRepository;
import com.blog.userCrud.repositories.SupportRepository;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class GameServiceImpl implements GameService {

	@Autowired(required = true)
	private GameRepository gameRepository;

	@Autowired(required = true)
	private SummaryRepository summaryRepository;
	
	@Autowired(required = true)
	private SupportRepository supportRepository;

	@Override
	public ResponseEntity<ApiResponseDto<?>> addNewGame(GamesDetailsRequestDto newGameDetails)
			throws GameAlreadyExistsException, ServiceLogicException {
		try {
			if (gameRepository.findGamesByName(newGameDetails.getName()) != null) {
				throw new GameAlreadyExistsException(
						"Add failed: Game already exists with name " + newGameDetails.getName());
			}

			Games newGames = new Games(newGameDetails.getName(), newGameDetails.getPicture(),
					newGameDetails.getDetail(), newGameDetails.getSalesVolume(), newGameDetails.getScore(),
					LocalDateTime.now());

			// save() is an in built method given by JpaRepository
			gameRepository.save(newGames);

			return ResponseEntity.status(HttpStatus.CREATED).body(
					new ApiResponseDto<>(ApiResponseStatus.SUCCESS.name(), "New Game has been successfully added!"));

		} catch (GameAlreadyExistsException e) {
			throw new GameAlreadyExistsException(e.getMessage());
		} catch (Exception e) {
			log.error("Failed to add new game: " + e.getMessage());
			throw new ServiceLogicException();
		}
	}

	@Override
	public ResponseEntity<ApiResponseDto<?>> getAllGames() throws ServiceLogicException {
		try {
			List<Games> games = gameRepository.findAllGamesByOrderByRegDateTimeDesc();

			return ResponseEntity.status(HttpStatus.OK)
					.body(new ApiResponseDto<>(ApiResponseStatus.SUCCESS.name(), games));

		} catch (Exception e) {
			log.error("Failed to fetch all games: " + e.getMessage());
			throw new ServiceLogicException();
		}
	}
	
	@Override
	public ResponseEntity<ApiResponseDto<?>> getGameById(String id) throws GameNotFoundException, ServiceLogicException {
		try {
			Games game = gameRepository.findGamesById(id);
			if (game == null)
				throw new GameNotFoundException("Game not found with id " + id);

			return ResponseEntity.status(HttpStatus.OK)
					.body(new ApiResponseDto<>(ApiResponseStatus.SUCCESS.name(), game));

		} catch (GameNotFoundException e) {
			throw new GameNotFoundException(e.getMessage());
		} catch (Exception e) {
			log.error("Failed to fetch the game: " + e.getMessage());
			throw new ServiceLogicException();
		}
	}

	@Override
	public ResponseEntity<ApiResponseDto<?>> updateGame(GamesDetailsRequestDto newGameDetails, String id)
			throws GameNotFoundException, ServiceLogicException {
		try {
			Games game = gameRepository.findGamesById(id);
			if (game == null)
				throw new GameNotFoundException("Game not found with id " + id);

			game.setName(newGameDetails.getName());
			game.setPicture(newGameDetails.getPicture());
			game.setDetail(newGameDetails.getDetail());
			game.setSalesVolume(newGameDetails.getSalesVolume());
			game.setScore(newGameDetails.getScore());

			gameRepository.save(game);

			return ResponseEntity.status(HttpStatus.OK)
					.body(new ApiResponseDto<>(ApiResponseStatus.SUCCESS.name(), "Game updated successfully!"));

		} catch (GameNotFoundException e) {
			throw new GameNotFoundException(e.getMessage());
		} catch (Exception e) {
			log.error("Failed to update game: " + e.getMessage());
			throw new ServiceLogicException();
		}
	}

	@Override
	public ResponseEntity<ApiResponseDto<?>> deleteGame(String id) throws ServiceLogicException, GameNotFoundException {
		try {
			Games game = gameRepository.findGamesById(id);
			if (game == null) {
				throw new GameNotFoundException("Game not found with id " + id);
			}

			gameRepository.delete(game);

			return ResponseEntity.status(HttpStatus.OK)
					.body(new ApiResponseDto<>(ApiResponseStatus.SUCCESS.name(), "Game deleted successfully!"));
		} catch (GameNotFoundException e) {
			throw new GameNotFoundException(e.getMessage());
		} catch (Exception e) {
			log.error("Failed to delete game: " + e.getMessage());
			throw new ServiceLogicException();
		}
	}

	@Override
	public ResponseEntity<ApiResponseDto<?>> getGameSummary() throws ServiceLogicException {
		try {
			Summary summary = summaryRepository.findTop1ByOrderByIdAsc();

			return ResponseEntity.status(HttpStatus.OK)
					.body(new ApiResponseDto<>(ApiResponseStatus.SUCCESS.name(), summary));

		} catch (Exception e) {
			log.error("Failed to fetch Game summary: " + e.getMessage());
			throw new ServiceLogicException();
		}
	}

	@Override
	public ResponseEntity<ApiResponseDto<?>> updateGameSummary(SummaryRequestDto summaryDetails)
			throws ServiceLogicException {
		try {
			Summary summary = summaryRepository.findTop1ByOrderByIdAsc();
			if (summary == null) {
				summary = new Summary(summaryDetails.getTitle(), summaryDetails.getBody(), summaryDetails.getUpdate());
			} else {
				summary.setTitle(summaryDetails.getTitle());
				summary.setBody(summaryDetails.getBody());
				summary.setUpdate(summaryDetails.getUpdate());
			}

			summaryRepository.save(summary);

			return ResponseEntity.status(HttpStatus.OK)
					.body(new ApiResponseDto<>(ApiResponseStatus.SUCCESS.name(), "Game updated successfully!"));

		} catch (Exception e) {
			log.error("Failed to update summary: " + e.getMessage());
			throw new ServiceLogicException();
		}
	}

	@Override
	public ResponseEntity<ApiResponseDto<?>> getAllQA() throws ServiceLogicException {
		try {
			List<Support> support = supportRepository.findAllByOrderByIdDesc();

			return ResponseEntity.status(HttpStatus.OK)
					.body(new ApiResponseDto<>(ApiResponseStatus.SUCCESS.name(), support));

		} catch (Exception e) {
			log.error("Failed to fetch all QA: " + e.getMessage());
			throw new ServiceLogicException();
		}
	}

	@Override
	public ResponseEntity<ApiResponseDto<?>> addNewQA(SupportRequestDto newQa)
			throws QaAlreadyExistsException, ServiceLogicException {
		try {
			if (supportRepository.findSupportByQuestion(newQa.getQuestion()) != null) {
				throw new QaAlreadyExistsException(
						"Add failed: Question already exists with name " + newQa.getQuestion());
			}

			Support newQA = new Support(newQa.getQuestion(), newQa.getAnswer());

			// save() is an in built method given by JpaRepository
			supportRepository.save(newQA);

			return ResponseEntity.status(HttpStatus.CREATED).body(
					new ApiResponseDto<>(ApiResponseStatus.SUCCESS.name(), "New QA has been successfully added!"));

		} catch (QaAlreadyExistsException e) {
			throw new QaAlreadyExistsException(e.getMessage());
		} catch (Exception e) {
			log.error("Failed to add new QA: " + e.getMessage());
			throw new ServiceLogicException();
		}
	}

	@Override
	public ResponseEntity<ApiResponseDto<?>> updateQA(SupportRequestDto newQa, String id)
			throws QaNotFoundException, ServiceLogicException {
		try {
			Support qa = supportRepository.findById(id);
			if (qa == null)
				throw new QaNotFoundException("Question not found with id " + id);

			qa.setQuestion(newQa.getQuestion());
			qa.setAnswer(newQa.getAnswer());

			supportRepository.save(qa);

			return ResponseEntity.status(HttpStatus.OK)
					.body(new ApiResponseDto<>(ApiResponseStatus.SUCCESS.name(), "QA updated successfully!"));

		} catch (QaNotFoundException e) {
			throw new QaNotFoundException(e.getMessage());
		} catch (Exception e) {
			log.error("Failed to update QA: " + e.getMessage());
			throw new ServiceLogicException();
		}
	}

	@Override
	public ResponseEntity<ApiResponseDto<?>> deleteQA(String id) throws ServiceLogicException, QaNotFoundException {
		try {
			Support qa = supportRepository.findById(id);
			if (qa == null) {
				throw new QaNotFoundException("QA not found with id " + id);
			}

			supportRepository.delete(qa);

			return ResponseEntity.status(HttpStatus.OK)
					.body(new ApiResponseDto<>(ApiResponseStatus.SUCCESS.name(), "QA deleted successfully!"));
		} catch (QaNotFoundException e) {
			throw new QaNotFoundException(e.getMessage());
		} catch (Exception e) {
			log.error("Failed to delete QA: " + e.getMessage());
			throw new ServiceLogicException();
		}
	}

}
