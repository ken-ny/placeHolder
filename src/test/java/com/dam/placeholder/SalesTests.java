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

import com.dam.placeholder.RestApiController;
import com.dam.placeholder.entity.Sales;
import com.dam.placeholder.repo.SaleDetailsRepository;
import com.dam.placeholder.repo.SalesRepository;
import com.dam.placeholder.response.SaleDetailsResponse;
import com.dam.placeholder.response.SalesResponse;

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

	@Test
	void shouldReturnListOf2SalesWithDetails() {
		when(salesRepo.findAll()).thenReturn(utils.mockSalesList(true));

		ResponseEntity<List<SalesResponse>> response = controller.getAllSales();

		List<SalesResponse> body = response.getBody();
		assertEquals(body.size(), 2);
		assertSubSalesListIsCorrect(body.get(0).getDetails());

		assertEquals(response.getStatusCode(), HttpStatus.OK);
	}

	@Test
	void shouldCreatedSaleWithDetailsWithId1WhenThereIsNoPreviousRecords() {
		when(salesRepo.findTopByOrderByIdDesc()).thenReturn(null);
		when(salesRepo.save(Mockito.any())).thenReturn(utils.mockSales());
		when(detailsRepo.save(Mockito.any())).thenReturn(utils.mockSaleDetails());
		ResponseEntity<SalesResponse> response = controller.postCreateSale(utils.createSalesRequestMock(false));

		SalesResponse body = response.getBody();
		assertEquals(body.getId(), 1);
		assertEquals(response.getStatusCode(), HttpStatus.OK);

	}

	@Test
	void shouldCreatedSaleWithDetailsWithId2WhenThereIsPreviousRecords() {
		Sales ex = utils.mockSales();
		ex.setId(2);
		when(salesRepo.save(Mockito.any())).thenReturn(ex);
		when(salesRepo.findTopByOrderByIdDesc()).thenReturn(utils.mockSales());
		when(detailsRepo.save(Mockito.any())).thenReturn(utils.mockSaleDetails());

		ResponseEntity<SalesResponse> response = controller.postCreateSale(utils.createSalesRequestMock(false));

		SalesResponse body = response.getBody();
		assertEquals(body.getId(), 2);
		assertEquals(response.getStatusCode(), HttpStatus.OK);
	}

	@Test
	void shouldUpdatedSaleWithDetailsWithId1WhenThereIsPreviousRecordsWithSameData() {
		when(salesRepo.save(Mockito.any())).thenReturn(utils.mockSales());
		when(salesRepo.findBySaleDateAndSalePrice(utils.mockSales().getSaleDate(), utils.mockSales().getSalePrice()))
				.thenReturn(utils.mockSales());

		ResponseEntity<SalesResponse> response = controller.postCreateSale(utils.createSalesRequestMock(false));

		SalesResponse body = response.getBody();
		assertEquals(body.getId(), 1);
		assertEquals(response.getStatusCode(), HttpStatus.OK);
	}

	@Test
	void shouldReturnSaleWhenCorrectId() {

		when(salesRepo.findById(1)).thenReturn(Optional.of(utils.mockSales()));

		ResponseEntity<SalesResponse> response = controller.getSingleSale(1);
		SalesResponse body = response.getBody();
		assertEquals(body.getId(), 1);
		assertEquals(response.getStatusCode(), HttpStatus.OK);
	}

	private void assertSubSalesListIsCorrect(List<SaleDetailsResponse> details) {
		assertEquals(details.size(), 1);
		assertEquals(details.get(0).getId(), new SaleDetailsResponse(utils.mockSaleDetails()).getId());
		assertEquals(details.get(0).getQuantity(), new SaleDetailsResponse(utils.mockSaleDetails()).getQuantity());
	}
}
