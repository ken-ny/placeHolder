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

import com.dam.placeholder.entity.Product;
import com.dam.placeholder.repo.ProductRepository;
import com.dam.placeholder.response.ExpansionResponse;
import com.dam.placeholder.response.ProductResponse;

@SpringBootTest
public class ProductTests {

	@InjectMocks
	RestApiController controller;

	@Mock
	ProductRepository productRepo;

	TestUtils utils;

	@BeforeEach
	void beforeEach() {
		controller = new RestApiController();
		controller.productRepo = productRepo;
		utils = new TestUtils();
	}

	// PRODUCTS TESTS
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
	void shouldCreatedProductWithDetailsWithId1WhenThereIsNoPreviousRecords() {
		when(productRepo.findTopByOrderByIdDesc()).thenReturn(null);
		when(productRepo.save(Mockito.any())).thenReturn(utils.mockProduct());

		ResponseEntity<ProductResponse> response = controller.postCreateProduct(utils.createProductRequestMock(false));

		ProductResponse body = response.getBody();
		assertEquals(body.getId(), 1);
		assertEquals(response.getStatusCode(), HttpStatus.OK);

	}

	@Test
	void shouldCreatedProductWithDetailsWithId2WhenThereIsPreviousRecords() {
		Product ex = utils.mockProduct();
		ex.setId(2);
		when(productRepo.save(Mockito.any())).thenReturn(ex);
		when(productRepo.findTopByOrderByIdDesc()).thenReturn(utils.mockProduct());
		ResponseEntity<ProductResponse> response = controller.postCreateProduct(utils.createProductRequestMock(false));

		ProductResponse body = response.getBody();
		assertEquals(body.getId(), 2);
		assertEquals(response.getStatusCode(), HttpStatus.OK);
	}

	@Test
	void shouldUpdatedProductWithDetailsWithId1WhenThereIsPreviousRecordsWithSameData() {
		when(productRepo.save(Mockito.any())).thenReturn(utils.mockProduct());
		when(productRepo.findByNameAndRarity(utils.mockProduct().getName(), utils.mockProduct().getRarity()))
				.thenReturn(utils.mockProduct());
		ResponseEntity<ProductResponse> response = controller.postCreateProduct(utils.createProductRequestMock(false));

		ProductResponse body = response.getBody();
		assertEquals(body.getId(), 1);
		assertEquals(response.getStatusCode(), HttpStatus.OK);
	}

	@Test
	void shouldReturnProductWhenCorrectId() {

		when(productRepo.findById(1)).thenReturn(Optional.of(utils.mockProduct()));

		ResponseEntity<ProductResponse> response = controller.getSingleProduct(1);
		ProductResponse body = response.getBody();
		assertEquals(body.getId(), 1);
		assertEquals(response.getStatusCode(), HttpStatus.OK);
	}

	private void assertSubExpansionListIsCorrect(List<ExpansionResponse> list) {
		assertEquals(list.size(), 1);
		assertEquals(list.get(0).getId(), new ExpansionResponse(utils.mockExpansion()).getId());
		assertEquals(list.get(0).getName(), new ExpansionResponse(utils.mockExpansion()).getName());
	}
}
