package com.dam.placeholder;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
import com.google.common.collect.Iterables;

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
		Expansion game = expansionRepo.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid expansion Id:" + id));
		model.addAttribute("expansion", game);
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
		Card game = cardRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid card Id:" + id));

		model.addAttribute("card", game);
		return "updateCard";
	}

	@PostMapping("/saveOffer")
	public String postUpdateCard(@ModelAttribute Offers offer) {
		offersRepo.save(offer);
		return "redirect:/cardMain";

	}

	@GetMapping("/createCard")
	public String showCardUpdateForm(Model model) {
		model.addAttribute("card", new Card(findNextAvailableId(PRODUCT)));
		model.addAttribute("expansionList", expansionRepo.findAll());
		return "createCard";
	}

	@PostMapping("/createOffer")
	public String postCreateOffer(@ModelAttribute Card card, Model model) {

		Optional<Card> existingCard = cardRepo.findByNameAndRarity(card.getName(), card.getRarity());
		Expansion inputExpansion = Iterables.getOnlyElement(card.getExpansion());
		if (existingCard.isPresent()) {
			existingCard.get().getExpansion().addAll(card.getExpansion());
			card = existingCard.get();
		}

		Card newCard = cardRepo.save(card);

		model.addAttribute("offer", new Offers(newCard, inputExpansion, findNextAvailableId(OFFER)));

		return "createOffer";

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

	// OFFERS
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

}
