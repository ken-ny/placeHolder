package com.dam.placeholder;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

import com.dam.placeholder.repo.ExpansionRepository;

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

//	@Test
//	void shouldReturnListOf2ExpansionsWithDetails() {
//
//		ResponseEntity<List<ExpansionResponse>> response = controller.getAllExpansion();
//
//		List<ExpansionResponse> body = response.getBody();
//		assertEquals(body.size(), 2);
//		assertExpansionIsCorrect(body.get(0), utils.expectedExpansionNREResponseDBT08());
//		assertExpansionIsCorrect(body.get(1), utils.expectedExpansionNREResponseBROTH());
//		assertSubGameIsCorrect(body.get(0).getGame(), utils.expectedGameNREResponseCFV());
//		assertSubGameIsCorrect(body.get(1).getGame(), utils.expectedGameNREResponseMTG());
//		assertEquals(response.getStatusCode(), HttpStatus.OK);
//
//	}

}
