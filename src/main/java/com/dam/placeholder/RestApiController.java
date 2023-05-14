package com.dam.placeholder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.dam.placeholder.entity.Expansion;
import com.dam.placeholder.entity.Game;
import com.dam.placeholder.entity.Offers;
import com.dam.placeholder.entity.Product;
import com.dam.placeholder.entity.SaleDetails;
import com.dam.placeholder.entity.Sales;
import com.dam.placeholder.repo.ExpansionRepository;
import com.dam.placeholder.repo.GameRepository;
import com.dam.placeholder.repo.OffersRepository;
import com.dam.placeholder.repo.ProductRepository;
import com.dam.placeholder.repo.SaleDetailsRepository;
import com.dam.placeholder.repo.SalesRepository;
import com.dam.placeholder.request.ExpansionRequest;
import com.dam.placeholder.request.GameRequest;
import com.dam.placeholder.request.OffersRequest;
import com.dam.placeholder.request.ProductRequest;
import com.dam.placeholder.request.SaleDetailsRequest;
import com.dam.placeholder.request.SalesRequest;
import com.dam.placeholder.response.ExpansionResponse;
import com.dam.placeholder.response.GameResponse;
import com.dam.placeholder.response.OffersResponse;
import com.dam.placeholder.response.ProductResponse;
import com.dam.placeholder.response.SalesResponse;
import com.dam.placeholder.response.utils.ErrorCodes;
import com.dam.placeholder.response.utils.ResponseUtils;

@Controller
public class RestApiController {

	private static final String NOT_ENOUGH_QUANTITY_TO_DECREASE = "Not enough quantity to decrease";

	private static final String EXPANSION = "E";

	private static final String PRODUCT = "P";

	private static final String GAME = "G";

	private static final String SALES = "S";

	private static final String SALE_DETAILS = "SD";

	private static final String OFFER = "O";

	@Autowired
	ProductRepository productRepo;

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

	// POST ENDPOINTS

	@PostMapping("/createProduct")
	public ResponseEntity<ProductResponse> postCreateProduct(@RequestBody ProductRequest product) {
		try {

			generateId(product);

			Product saveProduct = productRepo.save(new Product(product));

			return new ResponseEntity<>(new ProductResponse(saveProduct), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/createGame")
	public ResponseEntity<GameResponse> postCreateGame(@RequestBody GameRequest game) {
		try {
			generateId(game);

			Game savedGame = gameRepo.save(new Game(game));

			return new ResponseEntity<>(new GameResponse(savedGame), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/createExpansion")
	public ResponseEntity<ExpansionResponse> postCreateExpansion(@RequestBody ExpansionRequest expansion) {
		try {

			generateId(expansion);

			Expansion savedExpansion = expansionRepo.save(new Expansion(expansion));

			return new ResponseEntity<>(new ExpansionResponse(savedExpansion), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/createSale")
	public ResponseEntity<SalesResponse> postCreateSale(@RequestBody SalesRequest sale) {
		try {
			generateId(sale);

			// crea el objeto sales sin relaciones
			Sales newSale = salesRepo.save(new Sales(sale));
//			// crea las relaciones y las enlaza con el objeto sale
			sale.getDetails().stream().filter(Objects::nonNull).forEach(sd -> saveDetail(sd, newSale));
//
//			// actualiza el Sale con las nuevas relaciones
			Sales updatedSale = salesRepo.save(newSale);

			return new ResponseEntity<>(new SalesResponse(updatedSale), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/createOffer")
	public ResponseEntity<OffersResponse> postCreateOffer(@RequestBody OffersRequest offer) {
		try {
			generateId(offer);

			Offers savedOffer = offersRepo.save(new Offers(offer));

			return new ResponseEntity<>(new OffersResponse(savedOffer), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// PUT Mappings

	@PutMapping("/increaseQuantity/{offerId}/{quantity}/offers")
	public ResponseEntity<OffersResponse> postIncreaseQuantity(@PathVariable Integer offerId,
			@PathVariable Integer quantity) {
		try {

			Optional<Offers> foundOffer = offersRepo.findById(offerId);

			if (foundOffer.isPresent()) {
				foundOffer.get().increaseQuantity(quantity);
				Offers savedOffer = offersRepo.save(foundOffer.get());
				return new ResponseEntity<>(new OffersResponse(savedOffer), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(
						new OffersResponse(ResponseUtils.generateError(ErrorCodes.NOT_FOUND_RESULT.getCode(),
								ErrorCodes.NOT_FOUND_RESULT.getTitle(), null)),
						HttpStatus.INTERNAL_SERVER_ERROR);
			}

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/decreaseQuantity/{offerId}/{quantity}/offers")
	public ResponseEntity<OffersResponse> postDecreaseQuantity(@PathVariable Integer offerId,
			@PathVariable Integer quantity) {
		try {

			Optional<Offers> foundOffer = offersRepo.findById(offerId);

			if (foundOffer.isPresent()) {
				boolean hasError = foundOffer.get().decreaseQuantity(quantity);

				Offers savedOffer = offersRepo.save(foundOffer.get());
				if (hasError) {
					return new ResponseEntity<>(new OffersResponse(savedOffer), HttpStatus.OK);
				} else {
					return new ResponseEntity<>(
							new OffersResponse(ResponseUtils.generateError(ErrorCodes.GENERIC_ERROR.getCode(),
									ErrorCodes.GENERIC_ERROR.getTitle(), NOT_ENOUGH_QUANTITY_TO_DECREASE)),
							HttpStatus.EXPECTATION_FAILED);
				}
			} else {
				return new ResponseEntity<>(
						new OffersResponse(ResponseUtils.generateError(ErrorCodes.NOT_FOUND_RESULT.getCode(),
								ErrorCodes.NOT_FOUND_RESULT.getTitle(), null)),
						HttpStatus.INTERNAL_SERVER_ERROR);
			}

		} catch (Exception e) {
			return new ResponseEntity<>(
					new OffersResponse(ResponseUtils.generateError(ErrorCodes.GENERIC_ERROR.getCode(),
							ErrorCodes.GENERIC_ERROR.getTitle(), e.getMessage())),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// GET ENDPOINTS

	@GetMapping("/getExpansion/{expansionId}")
	public ResponseEntity<ExpansionResponse> getSingleExpansion(@PathVariable Integer expansionId) {
		try {

			Optional<Expansion> foundExpansion = expansionRepo.findById(expansionId);

			ExpansionResponse response = new ExpansionResponse(foundExpansion.get());

			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/getAllExpansions")
	public ResponseEntity<List<ExpansionResponse>> getAllExpansion() {
		try {

			List<Expansion> retrievedExpansions = expansionRepo.findAll();

			List<ExpansionResponse> response = new ArrayList<>();
			retrievedExpansions.stream().filter(Objects::nonNull)
					.forEach(expansion -> response.add(new ExpansionResponse(expansion)));

			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "/getGame/{gameId}", produces = "application/json;charset=UTF-8")
	public ResponseEntity<GameResponse> getSingleGame(@PathVariable Integer gameId) {
		try {

			Optional<Game> retrievedGames = gameRepo.findById(gameId);

			return new ResponseEntity<>(new GameResponse(retrievedGames.get()), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "/getAllGames", produces = "application/json;charset=UTF-8")
	public ResponseEntity<List<GameResponse>> getAllGames() {
		try {

			List<Game> retrievedGames = gameRepo.findAll();

			List<GameResponse> response = new ArrayList<>();
			retrievedGames.stream().filter(Objects::nonNull).forEach(game -> response.add(new GameResponse(game)));

			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "/getProduct/{productId}", produces = "application/json;charset=UTF-8")
	public ResponseEntity<ProductResponse> getSingleProduct(@PathVariable Integer productId) {
		try {

			Optional<Product> retrievedProducts = productRepo.findById(productId);

			return new ResponseEntity<>(new ProductResponse(retrievedProducts.get()), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "/getAllProducts", produces = "application/json;charset=UTF-8")
	public ResponseEntity<List<ProductResponse>> getAllProducts() {
		try {

			List<Product> retrievedProducts = productRepo.findAll();

			List<ProductResponse> response = new ArrayList<>();
			retrievedProducts.stream().filter(Objects::nonNull)
					.forEach(product -> response.add(new ProductResponse(product)));

			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "/getAllSales", produces = "application/json;charset=UTF-8")
	public ResponseEntity<List<SalesResponse>> getAllSales() {
		try {

			List<Sales> retrievedSales = salesRepo.findAll();

			List<SalesResponse> response = new ArrayList<>();
			retrievedSales.stream().filter(Objects::nonNull).forEach(sale -> response.add(new SalesResponse(sale)));

			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "/getSingleSale/{saleId}", produces = "application/json;charset=UTF-8")
	public ResponseEntity<SalesResponse> getSingleSale(@PathVariable Integer saleId) {
		try {

			Optional<Sales> foundSale = salesRepo.findById(saleId);

			return new ResponseEntity<>(new SalesResponse(foundSale.get()), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "/getAllOffers", produces = "application/json;charset=UTF-8")
	public ResponseEntity<List<OffersResponse>> getAllOffers() {
		try {

			List<Offers> retrievedOffers = offersRepo.findAll();

			List<OffersResponse> response = new ArrayList<>();
			retrievedOffers.stream().filter(Objects::nonNull).forEach(offer -> response.add(new OffersResponse(offer)));

			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "/getSingleOffer/{offerId}", produces = "application/json;charset=UTF-8")
	public ResponseEntity<OffersResponse> getSingleOffer(@PathVariable Integer offerId) {
		try {

			Optional<Offers> foundOffer = offersRepo.findById(offerId);

			return new ResponseEntity<>(new OffersResponse(foundOffer.get()), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// DELETE MAPPING

	@DeleteMapping(value = "/deleteGame/{gameId}", produces = "application/json;charset=UTF-8")
	public ResponseEntity<GameResponse> deleteGame(@PathVariable Integer gameId) {
		try {

			gameRepo.deleteById(gameId);

			return new ResponseEntity<>(null, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping(value = "/deleteExpansion/{expansionId}", produces = "application/json;charset=UTF-8")
	public ResponseEntity<GameResponse> deleteExpansion(@PathVariable Integer expansionId) {
		try {

			expansionRepo.deleteById(expansionId);

			return new ResponseEntity<>(null, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping(value = "/deleteProduct/{productId}", produces = "application/json;charset=UTF-8")
	public ResponseEntity<GameResponse> deleteProduct(@PathVariable Integer productId) {
		try {

			productRepo.deleteById(productId);

			return new ResponseEntity<>(null, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping(value = "/deleteSale/{saleId}", produces = "application/json;charset=UTF-8")
	public ResponseEntity<GameResponse> deleteSale(@PathVariable Integer saleId) {
		try {

			salesRepo.deleteById(saleId);

			return new ResponseEntity<>(null, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping(value = "/deleteOffer/{offerId}", produces = "application/json;charset=UTF-8")
	public ResponseEntity<OffersResponse> deleteOffer(@PathVariable Integer offerId) {
		try {

			offersRepo.deleteById(offerId);

			return new ResponseEntity<>(null, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// THYMELEAF
	// MAIN
	@GetMapping(value = "/")
	public String homePage(Model model) {
		model.addAttribute("totalGames", gameRepo.findAll().size());
		model.addAttribute("totalExpansions", expansionRepo.findAll().size());
		model.addAttribute("totalCards", productRepo.findAll().size());
		model.addAttribute("totalSells", salesRepo.findAll().size());
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
		Integer id = findNextAvailableId(GAME);
		Game newGame = new Game();
		newGame.setId(id);
		model.addAttribute("game", newGame);
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
		Expansion game = expansionRepo.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid expansion Id:" + id));

		model.addAttribute("expansion", game);
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
		Integer id = findNextAvailableId(EXPANSION);
		Expansion newExpansion = new Expansion();
		newExpansion.setId(id);
		model.addAttribute("expansion", newExpansion);
		return "createExpansion";
	}

	// PRODUCTS
	@GetMapping("/cardMain")
	public String cardsMain(Model model) {
		model.addAttribute("cardsList", productRepo.findAll());
		return "cardMain";
	}

	@GetMapping("/cardEdit/{id}")
	public String showCardUpdateForm(@PathVariable("id") Integer id, Model model) {
		Product game = productRepo.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid card Id:" + id));

		model.addAttribute("card", game);
		return "updateCard";
	}

	@PostMapping("/saveCard")
	public String postUpdateCard(@ModelAttribute Product game) {

		productRepo.save(game);
		return "redirect:/cardMain";

	}

	@GetMapping("/deleteCard/{id}")
	public String deleteCardThroughId(@PathVariable(value = "id") Integer id) {
		productRepo.deleteById(id);
		return "redirect:/cardMain";
	}

	@GetMapping("/createCard")
	public String showCardUpdateForm(Model model) {
		Integer id = findNextAvailableId(PRODUCT);
		Product newProduct = new Product();
		newProduct.setId(id);
		model.addAttribute("card", newProduct);
		return "createCard";
	}

	// SALES
	@GetMapping("/saleMain")
	public String saleMain(Model model) {
		model.addAttribute("salesList", salesRepo.findAll());
		return "saleMain";
	}

	@GetMapping("/saleEdit/{id}")
	public String showSaleUpdateForm(@PathVariable("id") Integer id, Model model) {
		Sales game = salesRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid sale Id:" + id));

		model.addAttribute("card", game);
		return "updateSale";
	}

	@PostMapping("/saveSale")
	public String postUpdateSale(@ModelAttribute Sales game) {

		salesRepo.save(game);
		return "redirect:/saleMain";

	}

	@GetMapping("/deleteSale/{id}")
	public String deleteSaleThroughId(@PathVariable(value = "id") Integer id) {
		salesRepo.deleteById(id);
		return "redirect:/saleMain";
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
		case PRODUCT:
			Product p = productRepo.findTopByOrderByIdDesc();
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
	 * @param sd
	 * @param newSale
	 */
	private void saveDetail(SaleDetailsRequest sd, Sales newSale) {

		/**
		 * En caso de que no llegue el id, será un detail nuevo ya que todos los campos
		 * se pueden repetir (se puede vender varias veces una carta de una misma
		 * expansion en diferentes ventas y con diferente o mismo precio)
		 */
		if (sd.getId() == null) {
			sd.setId(findNextAvailableId(SALE_DETAILS));
		}

		sd.setSale(newSale);
		SaleDetails newDetail = detailsRepo.save(new SaleDetails(sd));

		newSale.addDetail(newDetail);
	}

	/**
	 * Comprueba si el objeto de entrada tiene id, en caso de no tenerlo, comprueba
	 * en la base de datos si existe. Si no existe, calcula el siguiente id, si
	 * existe recupera el id y lo asigna para actualizarlo
	 * 
	 * @param product
	 */
	private void generateId(ProductRequest product) {
		if (product.getId() == null) {
			Product foundProduct = productRepo.findByNameAndRarity(product.getName(), product.getRarity());

			if (Objects.nonNull(foundProduct)) {
				product.setId(foundProduct.getId());
			} else {
				product.setId(findNextAvailableId(PRODUCT));
			}
		}
	}

	/**
	 * Comprueba si el objeto de entrada tiene id, en caso de no tenerlo, comprueba
	 * en la base de datos si existe. Si no existe, calcula el siguiente id, si
	 * existe recupera el id y lo asigna para actualizarlo
	 * 
	 * @param game
	 */
	private void generateId(GameRequest game) {
		if (game.getId() == null) {
			Game foundGameId = gameRepo.findByNameAndAbbreviation(game.getName(), game.getAbbreviation());

			if (Objects.nonNull(foundGameId)) {
				game.setId(foundGameId.getId());
			} else {
				game.setId(findNextAvailableId(GAME));
			}
		}
	}

	/**
	 * Comprueba si el objeto de entrada tiene id, en caso de no tenerlo, comprueba
	 * en la base de datos si existe. Si no existe, calcula el siguiente id, si
	 * existe recupera el id y lo asigna para actualizarlo
	 * 
	 * @param expansion
	 */
	private void generateId(ExpansionRequest expansion) {
		if (expansion.getId() == null) {
			Expansion foundExpansion = expansionRepo.findByNameAndAbbreviation(expansion.getName(),
					expansion.getAbbreviation());
			if (Objects.nonNull(foundExpansion)) {
				expansion.setId(foundExpansion.getId());
			} else {
				expansion.setId(findNextAvailableId(EXPANSION));
			}
		}
	}

	/**
	 * Comprueba si el objeto de entrada tiene id, en caso de no tenerlo, comprueba
	 * en la base de datos si existe. Si no existe, calcula el siguiente id, si
	 * existe recupera el id y lo asigna para actualizarlo
	 * 
	 * @param sale
	 */
	private void generateId(SalesRequest sale) {
		if (sale.getId() == null) {
			Sales foundSale = salesRepo.findBySaleDateAndSalePrice(sale.getSaleDate(), sale.getSalePrice());
			if (Objects.nonNull(foundSale)) {
				sale.setId(foundSale.getId());
			} else {
				sale.setId(findNextAvailableId(SALES));
			}
		}
	}

	/**
	 * Comprueba si el objeto de entrada tiene id, en caso de no tenerlo, comprueba
	 * en la base de datos si existe. Si no existe, calcula el siguiente id, si
	 * existe recupera el id y lo asigna para actualizarlo
	 * 
	 * @param offer
	 */
	private void generateId(OffersRequest offer) {
		if (offer.getId() == null) {
			Offers foundOffer = offersRepo.findByExpansionAndProduct(offer.getExpansion(), offer.getProduct());
			if (Objects.nonNull(foundOffer)) {
				offer.setId(foundOffer.getId());
			} else {
				offer.setId(findNextAvailableId(OFFER));
			}
		}
	}

}
