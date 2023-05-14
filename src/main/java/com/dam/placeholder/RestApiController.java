package com.dam.placeholder;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.dam.placeholder.entity.Card;
import com.dam.placeholder.entity.Expansion;
import com.dam.placeholder.entity.Game;
import com.dam.placeholder.entity.Offers;
import com.dam.placeholder.entity.SaleDetails;
import com.dam.placeholder.entity.Sales;
import com.dam.placeholder.repo.CardRepository;
import com.dam.placeholder.repo.ExpansionRepository;
import com.dam.placeholder.repo.GameRepository;
import com.dam.placeholder.repo.OffersRepository;
import com.dam.placeholder.repo.SaleDetailsRepository;
import com.dam.placeholder.repo.SalesRepository;

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

	// PUT Mappings

//	@PutMapping("/increaseQuantity/{offerId}/{quantity}/offers")
//	public ResponseEntity<OffersResponse> postIncreaseQuantity(@PathVariable Integer offerId,
//			@PathVariable Integer quantity) {
//		try {
//
//			Optional<Offers> foundOffer = offersRepo.findById(offerId);
//
//			if (foundOffer.isPresent()) {
//				foundOffer.get().increaseQuantity(quantity);
//				Offers savedOffer = offersRepo.save(foundOffer.get());
//				return new ResponseEntity<>(new OffersResponse(savedOffer), HttpStatus.OK);
//			} else {
//				return new ResponseEntity<>(
//						new OffersResponse(ResponseUtils.generateError(ErrorCodes.NOT_FOUND_RESULT.getCode(),
//								ErrorCodes.NOT_FOUND_RESULT.getTitle(), null)),
//						HttpStatus.INTERNAL_SERVER_ERROR);
//			}
//
//		} catch (Exception e) {
//			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
//
//	@PutMapping("/decreaseQuantity/{offerId}/{quantity}/offers")
//	public ResponseEntity<OffersResponse> postDecreaseQuantity(@PathVariable Integer offerId,
//			@PathVariable Integer quantity) {
//		try {
//
//			Optional<Offers> foundOffer = offersRepo.findById(offerId);
//
//			if (foundOffer.isPresent()) {
//				boolean hasError = foundOffer.get().decreaseQuantity(quantity);
//
//				Offers savedOffer = offersRepo.save(foundOffer.get());
//				if (hasError) {
//					return new ResponseEntity<>(new OffersResponse(savedOffer), HttpStatus.OK);
//				} else {
//					return new ResponseEntity<>(
//							new OffersResponse(ResponseUtils.generateError(ErrorCodes.GENERIC_ERROR.getCode(),
//									ErrorCodes.GENERIC_ERROR.getTitle(), NOT_ENOUGH_QUANTITY_TO_DECREASE)),
//							HttpStatus.EXPECTATION_FAILED);
//				}
//			} else {
//				return new ResponseEntity<>(
//						new OffersResponse(ResponseUtils.generateError(ErrorCodes.NOT_FOUND_RESULT.getCode(),
//								ErrorCodes.NOT_FOUND_RESULT.getTitle(), null)),
//						HttpStatus.INTERNAL_SERVER_ERROR);
//			}
//
//		} catch (Exception e) {
//			return new ResponseEntity<>(
//					new OffersResponse(ResponseUtils.generateError(ErrorCodes.GENERIC_ERROR.getCode(),
//							ErrorCodes.GENERIC_ERROR.getTitle(), e.getMessage())),
//					HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}

	// THYMELEAF
	// MAIN
	@GetMapping(value = "/")
	public String homePage(Model model) {
		model.addAttribute("totalGames", gameRepo.findAll().size());
		model.addAttribute("totalExpansions", expansionRepo.findAll().size());
		model.addAttribute("totalCards", cardRepo.findAll().size());
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
		model.addAttribute("cardsList", cardRepo.findAll());
		return "cardMain";
	}

	@GetMapping("/cardEdit/{id}")
	public String showCardUpdateForm(@PathVariable("id") Integer id, Model model) {
		Card game = cardRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid card Id:" + id));

		model.addAttribute("card", game);
		return "updateCard";
	}

	@PostMapping("/saveCard")
	public String postUpdateCard(@ModelAttribute Card game) {

		cardRepo.save(game);
		return "redirect:/cardMain";

	}

	@GetMapping("/deleteCard/{id}")
	public String deleteCardThroughId(@PathVariable(value = "id") Integer id) {
		cardRepo.deleteById(id);
		return "redirect:/cardMain";
	}

	@GetMapping("/createCard")
	public String showCardUpdateForm(Model model) {
		Integer id = findNextAvailableId(PRODUCT);
		Card newProduct = new Card();
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
	 * @param sd
	 * @param newSale
	 */
	private void saveDetail(SaleDetails sd, Sales newSale) {

		/**
		 * En caso de que no llegue el id, será un detail nuevo ya que todos los campos
		 * se pueden repetir (se puede vender varias veces una carta de una misma
		 * expansion en diferentes ventas y con diferente o mismo precio)
		 */
		if (sd.getId() == null) {
			sd.setId(findNextAvailableId(SALE_DETAILS));
		}

		sd.setSale(newSale);
		SaleDetails newDetail = detailsRepo.save(sd);

		newSale.addDetail(newDetail);
	}

	/**
	 * Comprueba si el objeto de entrada tiene id, en caso de no tenerlo, comprueba
	 * en la base de datos si existe. Si no existe, calcula el siguiente id, si
	 * existe recupera el id y lo asigna para actualizarlo
	 * 
	 * @param product
	 */
	private void generateId(Card product) {
		if (product.getId() == null) {
			Card foundProduct = cardRepo.findByNameAndRarity(product.getName(), product.getRarity());

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
	private void generateId(Game game) {
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
	private void generateId(Expansion expansion) {
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
	private void generateId(Sales sale) {
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
	private void generateId(Offers offer) {
		if (offer.getId() == null) {
			Offers foundOffer = offersRepo.findByExpansionAndCard(offer.getExpansion(), offer.getCard());
			if (Objects.nonNull(foundOffer)) {
				offer.setId(foundOffer.getId());
			} else {
				offer.setId(findNextAvailableId(OFFER));
			}
		}
	}

}
