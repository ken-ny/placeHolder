package com.dam.placeholder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.dam.placeholder.entity.Game;
import com.dam.placeholder.repo.GameRepository;
import com.dam.placeholder.response.ExpansionResponse;
import com.dam.placeholder.response.GameResponse;

@SpringBootTest
public class GamesTests {

	@InjectMocks
	RestApiController controller;

	@Mock
	GameRepository gameRepo;

	TestUtils utils;

	@BeforeEach
	void beforeEach() {
		controller = new RestApiController();
		controller.gameRepo = gameRepo;
		utils = new TestUtils();
	}

	@Test
	void shouldReturnListOf2GamesWithDetails() {
		when(gameRepo.findAll()).thenReturn(utils.mockGameList(true));

		ResponseEntity<List<GameResponse>> response = controller.getAllGames();

		List<GameResponse> body = response.getBody();
		assertEquals(body.size(), 2);
		assertSubExpansionListIsCorrect(body.get(0).getExpansions());

		assertEquals(response.getStatusCode(), HttpStatus.OK);
	}

	@Test
	void shouldCreatedGameWithDetailsWithId1WhenThereIsNoPreviousRecords() {
		when(gameRepo.findTopByOrderByIdDesc()).thenReturn(null);
		when(gameRepo.save(Mockito.any())).thenReturn(utils.mockGame());

		ResponseEntity<GameResponse> response = controller.postCreateGame(utils.createGameRequestMock(false, null));

		GameResponse body = response.getBody();
		assertEquals(body.getId(), 1);
		assertEquals(response.getStatusCode(), HttpStatus.OK);

	}

	@Test
	void shouldCreatedGameWithDetailsWithId2WhenThereIsPreviousRecords() {
		Game ex = utils.mockGame();
		ex.setId(2);
		when(gameRepo.save(Mockito.any())).thenReturn(ex);
		when(gameRepo.findTopByOrderByIdDesc()).thenReturn(utils.mockGame());
		ResponseEntity<GameResponse> response = controller.postCreateGame(utils.createGameRequestMock(false, null));

		GameResponse body = response.getBody();
		assertEquals(body.getId(), 2);
		assertEquals(response.getStatusCode(), HttpStatus.OK);
	}

	@Test
	void shouldUpdatedGameWithDetailsWithId1WhenThereIsPreviousRecordsWithSameData() {
		when(gameRepo.save(Mockito.any())).thenReturn(utils.mockGame());
		when(gameRepo.findByNameAndAbbreviation(utils.mockGame().getName(), utils.mockGame().getAbbreviation()))
				.thenReturn(utils.mockGame());
		ResponseEntity<GameResponse> response = controller.postCreateGame(utils.createGameRequestMock(false, null));

		GameResponse body = response.getBody();
		assertEquals(body.getId(), 1);
		assertEquals(response.getStatusCode(), HttpStatus.OK);
	}

	@Test
	void shouldReturnGameWhenCorrectId() {

		when(gameRepo.findById(1)).thenReturn(Optional.of(utils.mockGame()));

		ResponseEntity<GameResponse> response = controller.getSingleGame(1);
		GameResponse body = response.getBody();
		assertEquals(body.getId(), 1);
		assertEquals(response.getStatusCode(), HttpStatus.OK);
	}

	private void assertSubExpansionListIsCorrect(List<ExpansionResponse> list) {
		assertEquals(list.size(), 1);
		assertEquals(list.get(0).getId(), new ExpansionResponse(utils.mockExpansion()).getId());
		assertEquals(list.get(0).getName(), new ExpansionResponse(utils.mockExpansion()).getName());
	}

}
