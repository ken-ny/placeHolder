package com.dam.placeholder;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

import com.dam.placeholder.entity.Expansion;
import com.dam.placeholder.entity.Game;
import com.dam.placeholder.repo.ExpansionRepository;
import com.dam.placeholder.request.ExpansionRequest;
import com.dam.placeholder.response.ExpansionResponse;
import com.dam.placeholder.response.GameResponse;

@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest
class ExpansionNRETests {

	@InjectMocks
	RestApiController controller;

	@Autowired
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

		ResponseEntity<List<ExpansionResponse>> response = controller.getAllExpansion();

		List<ExpansionResponse> body = response.getBody();
		assertEquals(body.size(), 2);
		assertExpansionIsCorrect(body.get(0), utils.expectedExpansionNREResponseDBT08());
		assertExpansionIsCorrect(body.get(1), utils.expectedExpansionNREResponseBROTH());
		assertSubGameIsCorrect(body.get(0).getGame(), utils.expectedGameNREResponseCFV());
		assertSubGameIsCorrect(body.get(1).getGame(), utils.expectedGameNREResponseMTG());
		assertEquals(response.getStatusCode(), HttpStatus.OK);

	}

	@Test
	void shouldCreatedExpansionWithDetailsWithId3WhenThereIsNoPreviousRecordsWithSameData() {
		ExpansionRequest request = utils.createExpansionRequestMock(false, null);
		ResponseEntity<ExpansionResponse> response = controller.postCreateExpansion(request);

		ExpansionResponse body = response.getBody();
		assertExpansionIsCorrect(body, new Expansion(utils.createExpansionRequestMock(true, 3)));
		assertSubGameIsCorrect(body.getGame(), utils.expectedGameNREResponseCFV());
		assertEquals(response.getStatusCode(), HttpStatus.OK);

	}

	@Test
	void shouldUpdatedExpansionWithDetailsWithId1WhenThereIsIdInInput() {

		ResponseEntity<ExpansionResponse> response = controller
				.postCreateExpansion(utils.createExpansionRequestMock(true, 1));

		ExpansionResponse body = response.getBody();
		assertExpansionIsCorrect(body, new Expansion(utils.createExpansionRequestMock(true, 1)));
		assertSubGameIsCorrect(body.getGame(), utils.expectedGameNREResponseCFV());
		assertEquals(response.getStatusCode(), HttpStatus.OK);
	}

	@Test
	void shouldUpdatedExpansionWithDetailsWithId1WhenThereIsPreviousRecordsWithSameData() {

		ResponseEntity<ExpansionResponse> response = controller
				.postCreateExpansion(utils.createExistingExpansionRequest(false, null));

		ExpansionResponse body = response.getBody();
		assertExpansionIsCorrect(body, new Expansion(utils.createExistingExpansionRequest(true, 1)));
		assertSubGameIsCorrect(body.getGame(), utils.expectedGameNREResponseCFV());
		assertEquals(response.getStatusCode(), HttpStatus.OK);
	}

	@Test
	void shouldReturnExpansionWhenCorrectId() {

		ResponseEntity<ExpansionResponse> response = controller.getSingleExpansion(1);
		ExpansionResponse body = response.getBody();
		assertExpansionIsCorrect(body, utils.expectedExpansionNREResponseDBT08());
		assertSubGameIsCorrect(body.getGame(), utils.expectedGameNREResponseCFV());
		assertEquals(response.getStatusCode(), HttpStatus.OK);
	}

	private void assertSubGameIsCorrect(GameResponse game, Game expectedGameResponse) {
		GameResponse expected = new GameResponse(expectedGameResponse);
		assertEquals(game.getId(), expected.getId());
		assertEquals(game.getName(), expected.getName());
		assertEquals(game.getAbbreviation(), expected.getAbbreviation());
	}

	private void assertExpansionIsCorrect(ExpansionResponse expansionResponse, Expansion expectedExpansionNREResponse) {

		ExpansionResponse expected = new ExpansionResponse(expectedExpansionNREResponse);
		assertEquals(expansionResponse.getId(), expected.getId());
		assertEquals(expansionResponse.getAbbreviation(), expected.getAbbreviation());
		assertEquals(expansionResponse.getName(), expected.getName());

	}

}
