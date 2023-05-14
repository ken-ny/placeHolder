package com.dam.placeholder;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.dam.placeholder.repo.OffersRepository;

@SpringBootTest
public class OffersTests {

	@InjectMocks
	RestApiController controller;

	@Mock
	OffersRepository offersRepo;

	TestUtils utils;

	@BeforeEach
	void beforeEach() {
		controller = new RestApiController();
		controller.offersRepo = offersRepo;
		utils = new TestUtils();
	}

}
