package com.dam.placeholder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;

import com.dam.placeholder.entity.Card;
import com.dam.placeholder.entity.Expansion;
import com.dam.placeholder.entity.Game;
import com.dam.placeholder.entity.Offers;
import com.dam.placeholder.entity.Sales;
import com.dam.placeholder.entity.User;
import com.dam.placeholder.repo.CardMarketRelationRepository;
import com.dam.placeholder.repo.CardRepository;
import com.dam.placeholder.repo.ExpansionRepository;
import com.dam.placeholder.repo.GameRepository;
import com.dam.placeholder.repo.OffersRepository;
import com.dam.placeholder.repo.SaleDetailsRepository;
import com.dam.placeholder.repo.SalesRepository;
import com.dam.placeholder.repo.UserRepo;
import com.dam.placeholder.utils.Utils;

@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest
class RestApiControllerNRETests {

	private static final String MOCKED_RARITY = "C";

	private static final String MOCKED_NAME = "name";

	@InjectMocks
	RestApiController controller;

	@Autowired
	CardRepository cardRepo;

	@Autowired
	ExpansionRepository expansionRepo;

	@Autowired
	GameRepository gameRepo;

	@Autowired
	SalesRepository salesRepo;

	@Autowired
	SaleDetailsRepository detailsRepo;

	@Autowired
	OffersRepository offersRepo;

	@Autowired
	CardMarketRelationRepository marketRepo;

	@Autowired
	UserRepo userRepo;

	Model model = new ConcurrentModel();

	Utils utils = new Utils();

	@BeforeEach
	void beforeEach() {
		controller = new RestApiController();
		controller.expansionRepo = expansionRepo;
		controller.cardRepo = cardRepo;
		controller.detailsRepo = detailsRepo;
		controller.gameRepo = gameRepo;
		controller.marketRepo = marketRepo;
		controller.offersRepo = offersRepo;
		controller.salesRepo = salesRepo;
		controller.utils = utils;
		controller.userRepo = userRepo;
		utils = utils;
	}

	@Test
	void shouldReturnListOfAttributesInformed() {

		String response = controller.homePage(model);
		assertNotNull(response);
		assertEquals(response, "index");
		assertNotNull(model);
		assertNotNull(model.getAttribute("totalGames"));
		assertEquals(model.getAttribute("totalGames"), 2);
		assertNotNull(model.getAttribute("totalExpansions"));
		assertEquals(model.getAttribute("totalExpansions"), 12);
		assertNotNull(model.getAttribute("totalCards"));
		assertEquals(model.getAttribute("totalCards"), 3);
		assertNotNull(model.getAttribute("totalSells"));
		assertEquals(model.getAttribute("totalSells"), 2);
		assertNotNull(model.getAttribute("listSales"));
		assertNotNull(model.getAttribute("listSalesDates"));
		List<String> totalSalesDates = (List<String>) model.getAttribute("listSalesDates");
		List<Double> totalSales = (List<Double>) model.getAttribute("listSales");
		assertEquals(totalSales.size(), 2);
		assertEquals(totalSalesDates.size(), 2);
	}

	@Test
	void shouldReturnListOfAllGames() {
		String response = controller.gameMain(model);
		assertNotNull(response);
		assertEquals(response, "gameMain");
		assertNotNull(model);
		assertNotNull(model.getAttribute("gameList"));
		List<Game> gameList = (List<Game>) model.getAttribute("gameList");
		assertEquals(gameList.size(), 2);
	}

	@Test
	void shouldReturnASpecifiedExistingGame() {
		String response = controller.showGameUpdateForm(1, model);
		assertNotNull(response);
		assertEquals(response, "updateGame");
		assertNotNull(model);
		assertNotNull(model.getAttribute("game"));
		Game game = (Game) model.getAttribute("game");
		assertEquals(game.getId(), 1);
		assertEquals(game.getName(), "CARDFIGHT VANGUARD");
		assertEquals(game.getAbbreviation(), "CFV");
	}

	@Test
	void shouldThrowErrorWhenTheGameDoesNotExist() {
		try {
			String response = controller.showGameUpdateForm(10, model);
		} catch (Exception e) {
			assertEquals(e.getMessage(), "Invalid game Id:10");
		}
	}

	@Test
	void shouldReturnAnEmptyGameObjectWithNewId() {
		String response = controller.showGameUpdateForm(model);
		assertNotNull(response);
		assertEquals(response, "createGame");
		assertNotNull(model);
		assertNotNull(model.getAttribute("game"));
		Game game = (Game) model.getAttribute("game");
		assertNotNull(game.getId());
		assertEquals(game.getId(), 3);

	}

//-------------------------EXPANSION TESTS-------------------------------------------------------
	@Test
	void shouldReturnListOfAllExpansions() {
		String response = controller.expansionMain(model);
		assertNotNull(response);
		assertEquals(response, "expansionMain");
		assertNotNull(model);
		assertNotNull(model.getAttribute("expansionList"));
		List<Expansion> expansionList = (List<Expansion>) model.getAttribute("expansionList");
		assertEquals(expansionList.size(), 12);
	}

	@Test
	void shouldReturnASpecifiedExistingExpansionWithGameList() {
		String response = controller.showExpansionUpdateForm(1, model);
		assertNotNull(response);
		assertEquals(response, "updateExpansion");
		assertNotNull(model);
		assertNotNull(model.getAttribute("expansion"));
		assertNotNull(model.getAttribute("gameList"));
		Expansion expansion = (Expansion) model.getAttribute("expansion");
		List<Game> gameList = (List<Game>) model.getAttribute("gameList");
		assertEquals(expansion.getId(), 1);
		assertEquals(expansion.getName(), "D Booster Set 08: Minerva Rising");
		assertEquals(expansion.getAbbreviation(), "DBT08");
		assertEquals(gameList.size(), 2);
	}

	@Test
	void shouldThrowErrorWhenTheExpansionDoesNotExist() {
		try {
			String response = controller.showExpansionUpdateForm(10, model);
		} catch (Exception e) {
			assertEquals(e.getMessage(), "Invalid expansion Id:10");
		}
	}

	@Test
	void shouldReturnAnEmptyExpansionObjectWithNewIdAndGameList() {
		String response = controller.showExpansionUpdateForm(model);
		assertNotNull(response);
		assertEquals(response, "createExpansion");
		assertNotNull(model);
		assertNotNull(model.getAttribute("expansion"));
		assertNotNull(model.getAttribute("gameList"));
		Expansion expansion = (Expansion) model.getAttribute("expansion");
		List<Game> gameList = (List<Game>) model.getAttribute("gameList");
		assertNotNull(expansion.getId());
		assertEquals(expansion.getId(), 13);
		assertEquals(gameList.size(), 2);
	}

	// -------------------------CARDS TESTS---------------------
	@Test
	void shouldReturnListOfAllCards() {
		String response = controller.cardsMain(model);
		assertNotNull(response);
		assertEquals(response, "cardMain");
		assertNotNull(model);
		assertNotNull(model.getAttribute("offersList"));
		List<Offers> offerList = (List<Offers>) model.getAttribute("offersList");
		assertEquals(offerList.size(), 3);
	}

	@Test
	void shouldReturnASpecifiedExistingOfferWithExpansionList() {
		String response = controller.showCardUpdateForm(1, model);
		assertNotNull(response);
		assertEquals(response, "updateOffer");
		assertNotNull(model);
		assertNotNull(model.getAttribute("offer"));
		assertNotNull(model.getAttribute("expansionList"));
		Offers offer = (Offers) model.getAttribute("offer");
		List<Expansion> expansionList = (List<Expansion>) model.getAttribute("expansionList");
		assertEquals(offer.getId(), 1);
		assertEquals(offer.getCard().getName(), "Peak Personage Stealth Rogue, Shojodoji");
		assertEquals(offer.getCard().getRarity(), "RRR");
		assertEquals(offer.getExpansion().getName(), "D Booster Set 08: Minerva Rising");
		assertEquals(offer.getExpansion().getAbbreviation(), "DBT08");
		assertEquals(expansionList.size(), 12);
	}

	@Test
	void shouldThrowErrorWhenTheOfferDoesNotExist() {
		try {
			String response = controller.showCardUpdateForm(10, model);
		} catch (Exception e) {
			assertEquals(e.getMessage(), "Invalid card Id:10");
		}
	}

	@Test
	void shouldReturnAnEmptyCardObjectWithNewIdAndExpansionList() {
		String response = controller.showCardUpdateForm(model);
		assertNotNull(response);
		assertEquals(response, "createCard");
		assertNotNull(model);
		assertNotNull(model.getAttribute("card"));
		assertNotNull(model.getAttribute("expansionList"));
		Card card = (Card) model.getAttribute("card");
		List<Expansion> expansionList = (List<Expansion>) model.getAttribute("expansionList");
		assertNotNull(card.getId());
		assertEquals(card.getId(), 4);
		assertEquals(expansionList.size(), 12);
	}

	@Test
	void shouldReturnAnInformedOfferObjectWithNewId() {
		String response = controller.postCreateOffer(mockNewCard(), model);
		assertNotNull(response);
		assertEquals(response, "createOffer");
		assertNotNull(model);
		assertNotNull(model.getAttribute("offer"));
		Offers offer = (Offers) model.getAttribute("offer");
		Optional<Card> createdMockCard = cardRepo.findByNameAndRarity(MOCKED_NAME, MOCKED_RARITY);
		assertTrue(createdMockCard.isPresent());
		assertNotNull(offer.getId());
		assertEquals(offer.getId(), 4);
		assertEquals(createdMockCard.get().getId(), 4);
		assertEquals(createdMockCard.get().getName(), MOCKED_NAME);
		assertEquals(createdMockCard.get().getRarity(), MOCKED_RARITY);

	}

	@Test
	void shouldDecreaseQuantityTo3() {
		String response = controller.postDecreaseQuantity(1);
		assertNotNull(response);
		assertEquals(response, "redirect:/cardMain");
		Optional<Offers> foundOffer = offersRepo.findById(1);
		assertTrue(foundOffer.isPresent());
		assertEquals(foundOffer.get().getQuantity(), 3);

	}

	@Test
	void shouldReturnErrorPageWhenIdToDecreaseDoesNotExists() {
		String response = controller.postDecreaseQuantity(10);
		assertNotNull(response);
		assertEquals(response, "redirect:/errorPage");

	}

	@Test
	void shouldIncreaseQuantityTo5() {
		String response = controller.postIncreaseQuantity(1);
		assertNotNull(response);
		assertEquals(response, "redirect:/cardMain");
		Optional<Offers> foundOffer = offersRepo.findById(1);
		assertTrue(foundOffer.isPresent());
		assertEquals(foundOffer.get().getQuantity(), 5);

	}

	@Test
	void shouldReturnErrorPageWhenIdToIncreaseDoesNotExists() {
		String response = controller.postIncreaseQuantity(10);
		assertNotNull(response);
		assertEquals(response, "redirect:/errorPage");

	}

	private Card mockNewCard() {
		Optional<Expansion> existingExpansion = expansionRepo.findById(1);

		return new Card(4, MOCKED_NAME, MOCKED_RARITY, existingExpansion.get());
	}

	// -------------------------SALES TESTS--------------------------
	@Test
	void shouldReturnListOfAllSales() {
		String response = controller.saleMain(model);
		assertNotNull(response);
		assertEquals(response, "saleMain");
		assertNotNull(model);
		assertNotNull(model.getAttribute("salesList"));
		List<Sales> salesList = (List<Sales>) model.getAttribute("salesList");
		assertEquals(salesList.size(), 2);
	}

	@Test
	void shouldReturnDetailsOfAnExistingSale() {
		String response = controller.getSaleDetail(1, model);
		assertNotNull(response);
		assertEquals(response, "saleDetail");
		assertNotNull(model);
		assertNotNull(model.getAttribute("sale"));
		Sales sale = (Sales) model.getAttribute("sale");
		assertEquals(sale.getId(), 1);
		assertNotNull(sale.getDetails());
		assertEquals(sale.getStatus(), "sent");

	}

	@Test
	void shouldNotReturnDetailsOfAnNonExistingSale() {
		try {
			String response = controller.getSaleDetail(10, model);
		} catch (Exception e) {
			assertEquals(e.getMessage(), "Invalid sale Id:10");
		}
	}

	// -------------------------LOGIN TESTS--------------------------
	@Test
	void shouldNotReturnErrorWhenUserAndPasswordExists() {

		User newUser = new User();
		newUser.setEmail("prueba@email.com");
		newUser.setPassword("1");

		String response = controller.processLogin(newUser);

		assertEquals(response, "redirect:/main");

	}

	@Test
	void shouldReturnErrorWhenUserAndPasswordNotExists() {

		User newUser = new User();
		newUser.setEmail("prueba@email.com");
		newUser.setPassword("2");

		String response = controller.processLogin(newUser);

		assertEquals(response, "redirect:/loginError");

	}
}
