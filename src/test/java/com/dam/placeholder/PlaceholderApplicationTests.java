package com.dam.placeholder;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.dam.placeholder.repo.ProductRepository;

@SpringBootTest
class PlaceholderApplicationTests {

	@InjectMocks
	RestApiController controller;

	@Mock
	ProductRepository repository;

	@BeforeEach
	void beforeEach() {
		controller = new RestApiController();
		MockitoAnnotations.initMocks(this);

	}

	@Test
	void shouldReturnAProductOnDay14062022at10forProduct55245() {

	}
}
