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

import com.dam.placeholder.entity.Expansion;
import com.dam.placeholder.repo.ExpansionRepository;
import com.dam.placeholder.response.ExpansionResponse;
import com.dam.placeholder.response.GameResponse;

@SpringBootTest
class ExpansionTests {

	@InjectMocks
	RestApiController controller;

	@Mock
	ExpansionRepository expansionRepo;

	TestUtils utils;

	@BeforeEach
	void beforeEach() {
		controller = new RestApiController();
		controller.expansionRepo = expansionRepo;
		utils = new TestUtils();
	}

	@Test
	void shouldReturnListOf2ExpansionsWithDetails() {
		when(expansionRepo.findAll()).thenReturn(utils.mockExpansionList(true));

		ResponseEntity<List<ExpansionResponse>> response = controller.getAllExpansion();

		List<ExpansionResponse> body = response.getBody();
		assertEquals(body.size(), 2);
		assertSubGameIsCorrect(body.get(0).getGame());
		assertEquals(response.getStatusCode(), HttpStatus.OK);

	}

	@Test
	void shouldCreatedExpansionWithDetailsWithId1WhenThereIsNoPreviousRecords() {
		when(expansionRepo.findTopByOrderByIdDesc()).thenReturn(null);
		when(expansionRepo.save(Mockito.any())).thenReturn(utils.mockExpansion());

		ResponseEntity<ExpansionResponse> response = controller
				.postCreateExpansion(utils.createExpansionRequestMock(false, null));

		ExpansionResponse body = response.getBody();
		assertEquals(body.getId(), 1);
		assertEquals(response.getStatusCode(), HttpStatus.OK);

	}

	@Test
	void shouldCreatedExpansionWithDetailsWithId2WhenThereIsPreviousRecords() {
		Expansion ex = utils.mockExpansion();
		ex.setId(2);
		when(expansionRepo.save(Mockito.any())).thenReturn(ex);
		when(expansionRepo.findTopByOrderByIdDesc()).thenReturn(utils.mockExpansion());
		ResponseEntity<ExpansionResponse> response = controller
				.postCreateExpansion(utils.createExpansionRequestMock(false, null));

		ExpansionResponse body = response.getBody();
		assertEquals(body.getId(), 2);
		assertEquals(response.getStatusCode(), HttpStatus.OK);
	}

	@Test
	void shouldUpdatedExpansionWithDetailsWithId1WhenThereIsPreviousRecordsWithSameData() {
		when(expansionRepo.save(Mockito.any())).thenReturn(utils.mockExpansion());
		when(expansionRepo.findByNameAndAbbreviation(utils.mockExpansion().getName(),
				utils.mockExpansion().getAbbreviation())).thenReturn(utils.mockExpansion());
		ResponseEntity<ExpansionResponse> response = controller
				.postCreateExpansion(utils.createExpansionRequestMock(false, null));

		ExpansionResponse body = response.getBody();
		assertEquals(body.getId(), 1);
		assertEquals(response.getStatusCode(), HttpStatus.OK);
	}

	@Test
	void shouldReturnExpansionWhenCorrectId() {

		when(expansionRepo.findById(1)).thenReturn(Optional.of(utils.mockExpansion()));

		ResponseEntity<ExpansionResponse> response = controller.getSingleExpansion(1);
		ExpansionResponse body = response.getBody();
		assertEquals(body.getId(), 1);
		assertEquals(response.getStatusCode(), HttpStatus.OK);
	}

	private void assertSubGameIsCorrect(GameResponse game) {
		assertEquals(game.getId(), new GameResponse(utils.mockGame()).getId());
		assertEquals(game.getName(), new GameResponse(utils.mockGame()).getName());
	}

}
