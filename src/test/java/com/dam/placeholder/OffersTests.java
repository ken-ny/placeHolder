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

import com.dam.placeholder.entity.Offers;
import com.dam.placeholder.repo.OffersRepository;
import com.dam.placeholder.response.OffersResponse;

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

	@Test
	void shouldReturnListOf2OffersWithDetails() {
		when(offersRepo.findAll()).thenReturn(utils.mockOffersList(true));

		ResponseEntity<List<OffersResponse>> response = controller.getAllOffers();

		List<OffersResponse> body = response.getBody();
		assertEquals(body.size(), 2);
		assertOfferIsCorrect(body.get(0));

		assertEquals(response.getStatusCode(), HttpStatus.OK);
	}

	@Test
	void shouldCreatedProductWithDetailsWithId1WhenThereIsNoPreviousRecords() {
		when(offersRepo.findTopByOrderByIdDesc()).thenReturn(null);
		when(offersRepo.save(Mockito.any())).thenReturn(utils.mockOffers());

		ResponseEntity<OffersResponse> response = controller.postCreateOffer(utils.createOffersRequestMock(false));

		OffersResponse body = response.getBody();
		assertEquals(body.getId(), 1);
		assertEquals(response.getStatusCode(), HttpStatus.OK);

	}

	@Test
	void shouldCreatedProductWithDetailsWithId2WhenThereIsPreviousRecords() {
		Offers ex = utils.mockOffers();
		ex.setId(2);
		when(offersRepo.save(Mockito.any())).thenReturn(ex);
		when(offersRepo.findTopByOrderByIdDesc()).thenReturn(utils.mockOffers());
		ResponseEntity<OffersResponse> response = controller.postCreateOffer(utils.createOffersRequestMock(false));

		OffersResponse body = response.getBody();
		assertEquals(body.getId(), 2);
		assertEquals(response.getStatusCode(), HttpStatus.OK);
	}

	@Test
	void shouldUpdatedProductWithDetailsWithId1WhenThereIsPreviousRecordsWithSameData() {
		when(offersRepo.save(Mockito.any())).thenReturn(utils.mockOffers());
		when(offersRepo.findByExpansionAndProduct(utils.mockOffers().getExpansion(), utils.mockOffers().getProduct()))
				.thenReturn(utils.mockOffers());
		ResponseEntity<OffersResponse> response = controller.postCreateOffer(utils.createOffersRequestMock(false));

		OffersResponse body = response.getBody();
		assertEquals(body.getId(), 1);
		assertEquals(response.getStatusCode(), HttpStatus.OK);
	}

	@Test
	void shouldReturnProductWhenCorrectId() {

		when(offersRepo.findById(1)).thenReturn(Optional.of(utils.mockOffers()));

		ResponseEntity<OffersResponse> response = controller.getSingleOffer(1);
		OffersResponse body = response.getBody();
		assertEquals(body.getId(), 1);
		assertEquals(response.getStatusCode(), HttpStatus.OK);
	}

	private void assertOfferIsCorrect(OffersResponse offersResponse) {
		assertEquals(offersResponse.getId(), 1);
		assertEquals(offersResponse.getQuantity(), 15);
		assertEquals(offersResponse.getProduct().getId(), utils.mockProduct().getId());
		assertEquals(offersResponse.getProduct().getRarity(), utils.mockProduct().getRarity());
		assertEquals(offersResponse.getExpansion().getId(), utils.mockProduct().getId());
		assertEquals(offersResponse.getExpansion().getAbbreviation(), utils.mockExpansion().getAbbreviation());

	}

}
