package com.dam.placeholder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.dam.placeholder.cardmarket.Article;
import com.dam.placeholder.cardmarket.Order;
import com.dam.placeholder.cardmarket.Product;
import com.dam.placeholder.entity.Card;
import com.dam.placeholder.entity.CardMarketRelation;
import com.dam.placeholder.entity.Expansion;
import com.dam.placeholder.entity.Game;
import com.dam.placeholder.entity.Offers;
import com.dam.placeholder.entity.SaleDetails;
import com.dam.placeholder.entity.Sales;
import com.dam.placeholder.repo.CardMarketRelationRepository;
import com.dam.placeholder.repo.CardRepository;
import com.dam.placeholder.repo.ExpansionRepository;
import com.dam.placeholder.repo.GameRepository;
import com.dam.placeholder.repo.OffersRepository;
import com.dam.placeholder.repo.SaleDetailsRepository;
import com.dam.placeholder.repo.SalesRepository;
import com.dam.placeholder.utils.Utils;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Iterables;

@Controller
public class RestApiController {

	private static final String SALE_DATE = "saleDate";

	private static final String EXPANSION = "E";

	private static final String CARD = "C";

	private static final String GAME = "G";

	private static final String SALES = "S";

	private static final String SALE_DETAILS = "SD";

	private static final String OFFER = "O";

	private static final String formatoCardMarket = "yyyy-MM-dd";

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

	Utils utils = new Utils();

	// THYMELEAF
	// MAIN
	@GetMapping(value = "/")
	public String homePage(Model model) {
		model.addAttribute("totalGames", gameRepo.findAll().size());
		model.addAttribute("totalExpansions", expansionRepo.findAll().size());
		model.addAttribute("totalCards", offersRepo.findAll().size());
		model.addAttribute("totalSells", salesRepo.findAll().size());

		List<Sales> salesSorted = salesRepo.findAll(Sort.by(Sort.Direction.ASC, SALE_DATE));
		model.addAttribute("listSales", utils.extractSalesPrices(salesSorted));
		model.addAttribute("listSalesDates", utils.extractFormatedDates(salesSorted));
		return "index";

	}

	// GAMES
	@GetMapping("/gameMain")
	public String gameMain(Model model) {
		model.addAttribute("gameList", gameRepo.findAll());
		return "gameMain";
	}

	@GetMapping("/gameEdit/{id}")
	public String showGameUpdateForm(@PathVariable("id") Integer id, Model model) {
		Game game = gameRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid game Id:" + id));

		model.addAttribute("game", game);
		return "updateGame";
	}

	@PostMapping("/saveGame")
	public String postSaveGame(@ModelAttribute Game game) {
		gameRepo.save(game);
		return "redirect:/gameMain";
	}

	@GetMapping("/createGame")
	public String showGameUpdateForm(Model model) {
		model.addAttribute("game", new Game(findNextAvailableId(GAME)));
		return "createGame";
	}

	@GetMapping("/deleteGame/{id}")
	public String deleteGameThroughId(@PathVariable(value = "id") Integer id) {
		gameRepo.deleteById(id);
		return "redirect:/gameMain";

	}

	// EXPANSIONS
	@GetMapping("/expansionMain")
	public String expansionMain(Model model) {
		model.addAttribute("expansionList", expansionRepo.findAll());
		return "expansionMain";
	}

	@GetMapping("/expansionEdit/{id}")
	public String showExpansionUpdateForm(@PathVariable("id") Integer id, Model model) {
		Expansion expansion = expansionRepo.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid expansion Id:" + id));
		model.addAttribute("expansion", expansion);
		List<Game> availableGames = gameRepo.findAll();
		model.addAttribute("gameList", availableGames);
		return "updateExpansion";
	}

	@PostMapping("/saveExpansion")
	public String postUpdateExpansion(@ModelAttribute Expansion game) {

		expansionRepo.save(game);
		return "redirect:/expansionMain";

	}

	@GetMapping("/deleteExpansion/{id}")
	public String deleteExpansionThroughId(@PathVariable(value = "id") Integer id) {
		expansionRepo.deleteById(id);
		return "redirect:/expansionMain";
	}

	@GetMapping("/createExpansion")
	public String showExpansionUpdateForm(Model model) {
		List<Game> availableGames = gameRepo.findAll();
		model.addAttribute("gameList", availableGames);
		model.addAttribute("expansion", new Expansion(findNextAvailableId(EXPANSION)));

		return "createExpansion";
	}

	// CARDS
	@GetMapping("/cardMain")
	public String cardsMain(Model model) {
		model.addAttribute("offersList", offersRepo.findAll());
		return "cardMain";
	}

	@GetMapping("/offerEdit/{id}")
	public String showCardUpdateForm(@PathVariable("id") Integer id, Model model) {
		Offers game = offersRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid card Id:" + id));

		model.addAttribute("offer", game);
		model.addAttribute("expansionList", expansionRepo.findAll());

		return "updateOffer";
	}

	@PostMapping("/saveOffer")
	public String postUpdateCard(@ModelAttribute Offers offer) {
		offersRepo.save(offer);
		return "redirect:/cardMain";

	}

	@GetMapping("/createCard")
	public String showCardUpdateForm(Model model) {
		model.addAttribute("card", new Card(findNextAvailableId(CARD)));
		model.addAttribute("expansionList", expansionRepo.findAll());
		return "createCard";
	}

	@PostMapping("/createOffer")
	public String postCreateOffer(@ModelAttribute Card card, Model model) {

		Optional<Card> existingCard = cardRepo.findByNameAndRarity(card.getName(), card.getRarity());
		Expansion retrievedExpansion = Iterables.getOnlyElement(card.getExpansion());
		if (existingCard.isPresent()) {
			existingCard.get().addAllExpansions(card.getExpansion());
			card = existingCard.get();
		}

		Card newCard = cardRepo.save(card);

		model.addAttribute("offer", new Offers(newCard, retrievedExpansion, findNextAvailableId(OFFER)));

		return "createOffer";
	}

	@GetMapping("/offerDecreaseQuantity/{id}")
	public String postDecreaseQuantity(@PathVariable(value = "id") Integer offerId) {

		Optional<Offers> foundOffer = offersRepo.findById(offerId);

		if (foundOffer.isPresent()) {
			foundOffer.get().decreaseQuantity();

			offersRepo.save(foundOffer.get());
			return "redirect:/cardMain";

		} else {
			return "redirect:/errorPage";
		}

	}

	@GetMapping("/offerIncreaseQuantity/{id}")
	public String postIncreaseQuantity(@PathVariable(value = "id") Integer offerId) {

		Optional<Offers> foundOffer = offersRepo.findById(offerId);

		if (foundOffer.isPresent()) {
			foundOffer.get().increaseQuantity();

			offersRepo.save(foundOffer.get());
			return "redirect:/cardMain";

		} else {
			return "redirect:/errorPage";
		}

	}

	@GetMapping("/deleteOffer/{id}")
	public String deleteCardThroughId(@PathVariable(value = "id") Integer id) {
		offersRepo.deleteById(id);
		return "redirect:/cardMain";
	}

	// SALES
	@GetMapping("/saleMain")
	public String saleMain(Model model) {
		model.addAttribute("salesList", salesRepo.findAll());
		return "saleMain";
	}

	@GetMapping("/saleDetail/{id}")
	public String getSaleDetail(@PathVariable(value = "id") Integer id, Model model) {
		Sales sale = salesRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid sale Id:" + id));

		model.addAttribute("sale", sale);

		return "saleDetail";
	}

	// UPDATE WITH CARDMARKET
	@GetMapping("/update")
	public String getCardMarketUpdate(Model model) throws StreamReadException, DatabindException, IOException {

		List<Order> orders = retrieveCardMarketJson();

		orders.stream().filter(Objects::nonNull).forEach(order -> createSale(order));

		return "redirect:/";

	}

	// SETTINGS

	@GetMapping("/settings")
	public String settingsPage(Model model) {

		return "settings";

	}

	@GetMapping("/user")
	public String userPage(Model model) {
		return "user";

	}

	private void createSale(Order order) {
		Sales newSale = new Sales();

		newSale.setSalePrice(order.getTotalValue());
		newSale.setSaleDate(utils.convertDate(order.getState().getDateBought(), formatoCardMarket));
		newSale.setId(findNextAvailableId(SALES));
		newSale.setStatus(order.getState().getState());
		Optional<CardMarketRelation> cmr = marketRepo.findByIdOrder(order.getIdOrder());
		if (cmr.isPresent()) {
			newSale.setId(cmr.get().getSale().getId());
		} else {
			newSale = salesRepo.save(newSale);
			marketRepo.save(new CardMarketRelation(order.getIdOrder(), newSale));
			addDetails(order, newSale);

		}

		salesRepo.save(newSale);
	}

	/**
	 * @param order
	 * @param newSale
	 */
	private void addDetails(Order order, Sales newSale) {
		order.getArticle().stream().filter(Objects::nonNull).forEach(article -> saveDetail(article, newSale));
	}

	/**
	 * @return
	 * @throws IOException
	 * @throws StreamReadException
	 * @throws DatabindException
	 */
	private List<Order> retrieveCardMarketJson() throws IOException, StreamReadException, DatabindException {
		ObjectMapper mapper = new ObjectMapper();
		InputStream is = Order.class.getResourceAsStream("/cardmarket.json");
		return mapper.readValue(is, new TypeReference<>() {
		});
	}

	// Metodos
	/**
	 * Devuelve el siguiente id disponible para cada tabla. En caso de que la tabla
	 * esté vacía, devuelve 1 como primer id
	 * 
	 * @param table
	 * @return
	 */
	private Integer findNextAvailableId(String table) {

		switch (table) {
		case GAME:
			Game g = gameRepo.findTopByOrderByIdDesc();
			return (Objects.isNull(g)) ? 1 : g.getId() + 1;
		case CARD:
			Card p = cardRepo.findTopByOrderByIdDesc();
			return (Objects.isNull(p)) ? 1 : p.getId() + 1;
		case EXPANSION:
			Expansion e = expansionRepo.findTopByOrderByIdDesc();
			return (Objects.isNull(e)) ? 1 : e.getId() + 1;
		case SALES:
			Sales s = salesRepo.findTopByOrderByIdDesc();
			return (Objects.isNull(s)) ? 1 : s.getId() + 1;
		case SALE_DETAILS:
			SaleDetails sd = detailsRepo.findTopByOrderByIdDesc();
			return (Objects.isNull(sd)) ? 1 : sd.getId() + 1;
		case OFFER:
			Offers o = offersRepo.findTopByOrderByIdDesc();
			return (Objects.isNull(o)) ? 1 : o.getId() + 1;

		}

		return null;

	}

	/**
	 * Guarda cada detalle de la entrada relacionándolo con el objeto sales que se
	 * le pasa. Después añade el nuevo detail al objeto Sales
	 * 
	 * @param article
	 * @param newSale
	 */
	private void saveDetail(Article article, Sales newSale) {

		if (Objects.isNull(article.getProduct())) {
			return;
		}

		Product product = article.getProduct();
		Optional<Card> foundCard = cardRepo.findByNameAndRarity(product.getName(), product.getRarity());
		Optional<Expansion> foundExpansion = expansionRepo.findByName(product.getExpansion());
		SaleDetails newDetail = new SaleDetails(findNextAvailableId(SALE_DETAILS));

		if (!foundCard.isPresent()) {
			Card newCard = new Card(findNextAvailableId(CARD), product.getName(), product.getRarity(), null,
					product.getImage(), foundExpansion.get(), null, null);

			Card card = cardRepo.save(newCard);
			newDetail.setCard(card);
		} else {
			newDetail.setCard(foundCard.get());
		}
		newDetail.setExpansion(foundExpansion.get());
		newDetail.setQuantity(article.getCount());
		newDetail.setUnitaryPrice(article.getPrice());
		newDetail.setSale(newSale);
		SaleDetails savedDetail = detailsRepo.save(newDetail);

		newSale.addDetail(savedDetail);
	}

}
