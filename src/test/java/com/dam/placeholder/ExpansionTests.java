package com.dam.placeholder;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.dam.placeholder.repo.ExpansionRepository;

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

}
