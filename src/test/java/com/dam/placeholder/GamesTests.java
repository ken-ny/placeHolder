package com.dam.placeholder;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.dam.placeholder.repo.GameRepository;

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

}
