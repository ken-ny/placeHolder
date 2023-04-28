package com.dam.placeholder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.dam.placeholder.repo.ExpansionRepository;
import com.dam.placeholder.repo.GameRepository;
import com.dam.placeholder.repo.OffersRepository;
import com.dam.placeholder.repo.ProductRepository;
import com.dam.placeholder.repo.SaleDetailsRepository;
import com.dam.placeholder.repo.SalesRepository;
import com.dam.placeholder.response.ExpansionResponse;
import com.dam.placeholder.response.GameResponse;
import com.dam.placeholder.response.ProductResponse;

@SpringBootTest
class PlaceholderApplicationTests {

	@InjectMocks
	RestApiController controller;

	@Mock
	ExpansionRepository expansionRepo;

	@Mock
	ProductRepository productRepo;

	@Mock
	GameRepository gameRepo;

	@Mock
	SalesRepository salesRepo;

	@Mock
	SaleDetailsRepository detailsRepo;

	@Mock
	OffersRepository offersRepo;

	TestUtils utils;

	@BeforeEach
	void beforeEach() {
		controller = new RestApiController();
		controller.expansionRepo = expansionRepo;
		controller.detailsRepo = detailsRepo;
		controller.gameRepo = gameRepo;
		controller.offersRepo = offersRepo;
		controller.productRepo = productRepo;
		controller.salesRepo = salesRepo;
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
	void shouldReturnListOf2ProductsWithDetails() {
		when(productRepo.findAll()).thenReturn(utils.mockProductList(true));

		ResponseEntity<List<ProductResponse>> response = controller.getAllProducts();

		List<ProductResponse> body = response.getBody();
		assertEquals(body.size(), 2);
		assertSubExpansionListIsCorrect(body.get(0).getExpansions());

		assertEquals(response.getStatusCode(), HttpStatus.OK);

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

	private void assertSubExpansionListIsCorrect(List<ExpansionResponse> list) {
		assertEquals(list.size(), 1);
		assertEquals(list.get(0).getId(), new ExpansionResponse(utils.mockExpansion()).getId());
		assertEquals(list.get(0).getName(), new ExpansionResponse(utils.mockExpansion()).getName());
	}

	private void assertSubGameIsCorrect(GameResponse game) {
		assertEquals(game.getId(), new GameResponse(utils.mockGame()).getId());
		assertEquals(game.getName(), new GameResponse(utils.mockGame()).getName());
	}

}
