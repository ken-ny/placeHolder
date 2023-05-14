package com.dam.placeholder;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.dam.placeholder.repo.CardRepository;

@SpringBootTest
public class ProductTests {

	@InjectMocks
	RestApiController controller;

	@Mock
	CardRepository productRepo;

	TestUtils utils;

	@BeforeEach
	void beforeEach() {
		controller = new RestApiController();
		controller.cardRepo = productRepo;
		utils = new TestUtils();
	}

}
