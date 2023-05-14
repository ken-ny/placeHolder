package com.dam.placeholder;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.dam.placeholder.repo.SaleDetailsRepository;
import com.dam.placeholder.repo.SalesRepository;

@SpringBootTest
public class SalesTests {

	@InjectMocks
	RestApiController controller;

	@Mock
	SalesRepository salesRepo;

	@Mock
	SaleDetailsRepository detailsRepo;

	TestUtils utils;

	@BeforeEach
	void beforeEach() {
		controller = new RestApiController();
		controller.detailsRepo = detailsRepo;
		controller.salesRepo = salesRepo;
		utils = new TestUtils();
	}

}
