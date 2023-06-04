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
import org.thymeleaf.util.StringUtils;

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

	@Autowired
	UserRepo userRepo;

	Utils utils = new Utils();

	// LOGIN
	@GetMapping(value = "/")
	public String intro(Model model) {
		model.addAttribute("user", new User());

		return "login";
	}

	@GetMapping(value = "/register")
	public String register(Model model) {
		model.addAttribute("user", new User());

		return "register";
	}

	@PostMapping(value = "/register/process")
	public String processRegistation(@ModelAttribute User user) {

		userRepo.save(user);

		return "redirect:/";
	}

	@PostMapping(value = "/login/process")
	public String processLogin(@ModelAttribute User user) {
		User userBd = userRepo.findByEmail(user.getEmail());

		if (userBd != null && StringUtils.equals(user.getPassword(), userBd.getPassword())) {
			return "redirect:/dashboard";
		} else {
			return "redirect:/loginError";
		}

	}

	@GetMapping(value = "/loginError")
	public String errorLogin(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("error", "ERROR");
		model.addAttribute("details", "Usuario y/o contraseña incorrecto");
		return "login";
	}

	// MAIN
	@GetMapping(value = "/dashboard")
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
	@GetMapping("/games")
	public String gameMain(Model model) {
		model.addAttribute("gameList", gameRepo.findAll());
		return "gameMain";
	}

	@GetMapping("/game/edit/{id}")
	public String showGameUpdateForm(@PathVariable("id") Integer id, Model model) {
		Game game = gameRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid game Id:" + id));

		model.addAttribute("game", game);
		return "updateGame";
	}

	@PostMapping("/game/save")
	public String postSaveGame(@ModelAttribute Game game) {
		gameRepo.save(game);
		return "redirect:/games";
	}

	@GetMapping("/game/create")
	public String showGameUpdateForm(Model model) {
		model.addAttribute("game", new Game(findNextAvailableId(GAME)));
		return "createGame";
	}

	@GetMapping("/game/delete/{id}")
	public String deleteGameThroughId(@PathVariable(value = "id") Integer id) {
		gameRepo.deleteById(id);
		return "redirect:/games";

	}

	// EXPANSIONS
	@GetMapping("/expansion")
	public String expansionMain(Model model) {
		model.addAttribute("expansionList", expansionRepo.findAll());
		return "expansionMain";
	}

	@GetMapping("/expansion/edit/{id}")
	public String showExpansionUpdateForm(@PathVariable("id") Integer id, Model model) {
		Expansion expansion = expansionRepo.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid expansion Id:" + id));
		model.addAttribute("expansion", expansion);
		List<Game> availableGames = gameRepo.findAll();
		model.addAttribute("gameList", availableGames);
		return "updateExpansion";
	}

	@PostMapping("/expansion/save")
	public String postUpdateExpansion(@ModelAttribute Expansion game) {

		expansionRepo.save(game);
		return "redirect:/expansion";

	}

	@GetMapping("/expansion/delete/{id}")
	public String deleteExpansionThroughId(@PathVariable(value = "id") Integer id) {
		expansionRepo.deleteById(id);
		return "redirect:/expansion";
	}

	@GetMapping("/expansion/create")
	public String showExpansionUpdateForm(Model model) {
		List<Game> availableGames = gameRepo.findAll();
		model.addAttribute("gameList", availableGames);
		model.addAttribute("expansion", new Expansion(findNextAvailableId(EXPANSION)));

		return "createExpansion";
	}

	// CARDS

	@GetMapping("/cards")
	public String cardsList(Model model) {
		model.addAttribute("cardsList", cardRepo.findAll());
		return "cardList";
	}

	@GetMapping("/card/edit/{id}")
	public String showCardUpdateForm(@PathVariable("id") Integer id, Model model) {
		Card card = cardRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid card Id:" + id));

		model.addAttribute("card", card);
		model.addAttribute("expansionList", expansionRepo.findAll());

		return "updateCard";
	}

	@GetMapping("/card/create")
	public String showCardCreateForm(Model model) {
		model.addAttribute("card", new Card(findNextAvailableId(CARD)));
		model.addAttribute("expansionList", expansionRepo.findAll());
		return "updateCard";
	}

	@PostMapping("/card/update")
	public String postUpdateCard(@ModelAttribute Card card, Model model) {

		cardRepo.save(card);

		return "redirect:/cards";
	}

	@GetMapping("/card/delete/{id}")
	public String deleteCardThroughId(@PathVariable(value = "id") Integer id) {
		cardRepo.deleteById(id);
		return "redirect:/cards";
	}

	// OFFERS

	@GetMapping("/offers")
	public String cardsMain(Model model) {
		model.addAttribute("offersList", offersRepo.findAll());
		return "offerMain";
	}

	@GetMapping("/offers/edit/{id}")
	public String showCardOfferUpdateForm(@PathVariable("id") Integer id, Model model) {
		Offers offer = offersRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid card Id:" + id));

		model.addAttribute("offer", offer);
		model.addAttribute("expansionList", expansionRepo.findAll());

		return "updateOffer";
	}

	@PostMapping("/offer/save")
	public String postUpdateCard(@ModelAttribute Offers offer) {
		offersRepo.save(offer);
		return "redirect:/offers";

	}

	@GetMapping("/offer/card")
	public String showCardUpdateForm(Model model) {
		model.addAttribute("card", new Card(findNextAvailableId(CARD)));
		model.addAttribute("expansionList", expansionRepo.findAll());
		return "createCard";
	}

	@GetMapping("/offer/select")
	public String showCardSelectList(Model model) {
		model.addAttribute("cardsList", cardRepo.findAll());
		return "cardSelect";
	}

	@GetMapping("/offer/selected/{cardId}/{expansionId}")
	public String cardSelectedForOffer(@PathVariable("cardId") Integer idCard,
			@PathVariable("expansionId") Integer idExpansion, Model model) {
		Optional<Card> cardSelected = cardRepo.findById(idCard);
		Optional<Expansion> selectedExpansion = expansionRepo.findById(idExpansion);
		if (cardSelected.isPresent() && selectedExpansion.isPresent()) {
			model.addAttribute("offer",
					new Offers(cardSelected.get(), selectedExpansion.get(), findNextAvailableId(OFFER)));
			return "createOffer";
		} else {
			return "cardSelect";
		}
	}

	@PostMapping("/offer/create")
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

	@GetMapping("/offer/decrease/{id}")
	public String postDecreaseQuantity(@PathVariable(value = "id") Integer offerId) {

		Optional<Offers> foundOffer = offersRepo.findById(offerId);

		if (foundOffer.isPresent()) {
			foundOffer.get().decreaseQuantity();

			offersRepo.save(foundOffer.get());
			return "redirect:/offers";

		} else {
			return "redirect:/errorPage";
		}

	}

	@GetMapping("/offer/increase/{id}")
	public String postIncreaseQuantity(@PathVariable(value = "id") Integer offerId) {

		Optional<Offers> foundOffer = offersRepo.findById(offerId);

		if (foundOffer.isPresent()) {
			foundOffer.get().increaseQuantity();

			offersRepo.save(foundOffer.get());
			return "redirect:/offers";

		} else {
			return "redirect:/errorPage";
		}

	}

	@GetMapping("/offer/delete/{id}")
	public String deleteOfferThroughId(@PathVariable(value = "id") Integer id) {
		offersRepo.deleteById(id);
		return "redirect:/offers";
	}

	// SALES
	@GetMapping("/sales")
	public String saleMain(Model model) {
		model.addAttribute("salesList", salesRepo.findAll());
		return "saleMain";
	}

	@GetMapping("/sale/detail/{id}")
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

		return "redirect:/dashboard";

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

	/**
	 * Crea una nueva venta en la bd con la información del order que se le pasa. En
	 * caso de que la venta ya exista, actualiza la información. Crea un nuevo
	 * registro en la tabla CardMarketRelation relacionando el numero de order de
	 * cardmarket con el id de la venta para futuras referencias
	 * 
	 * @param order
	 */
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
	 * Guarda el detalle de la venta y lo con el objeto Sales que se le informa
	 * 
	 * @param order
	 * @param newSale
	 */
	private void addDetails(Order order, Sales newSale) {
		order.getArticle().stream().filter(Objects::nonNull).forEach(article -> saveDetail(article, newSale));
	}

	/**
	 * Recupera el archivo json de cardmarket y lo devuelve como una lista de Order
	 * para trabajar con el
	 * 
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
			Card newCard = new Card(findNextAvailableId(CARD), product.getName(), product.getRarity(),
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
