package com.dam.placeholder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dam.placeholder.entity.Expansion;
import com.dam.placeholder.entity.Game;
import com.dam.placeholder.entity.Product;
import com.dam.placeholder.repo.ExpansionRepository;
import com.dam.placeholder.repo.GameRepository;
import com.dam.placeholder.repo.ProductRepository;
import com.dam.placeholder.request.ExpansionRequest;
import com.dam.placeholder.request.GameRequest;
import com.dam.placeholder.request.ProductRequest;
import com.dam.placeholder.response.ExpansionResponse;
import com.dam.placeholder.response.GameResponse;
import com.dam.placeholder.response.ProductResponse;

@RestController
@RequestMapping("/placeHolder")
public class RestApiController {

	private static final String EXPANSION = "E";

	private static final String PRODUCT = "P";

	private static final String GAME = "G";

	@Autowired
	ProductRepository productRepo;

	@Autowired
	ExpansionRepository expansionRepo;

	@Autowired
	GameRepository gameRepo;

	// POST ENDPOINTS

	@PostMapping("/createProduct")
	public ResponseEntity<ProductResponse> postCreateProduct(@RequestBody ProductRequest prod) {
		try {

			prod.setId(findNextAvailableId(PRODUCT));

			Product saveProduct = productRepo.save(new Product(prod));

			return new ResponseEntity<>(new ProductResponse(saveProduct), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/createGame")
	public ResponseEntity<GameResponse> postCreateGame(@RequestBody GameRequest prod) {
		try {

			prod.setId(findNextAvailableId(GAME));
			Game saveGame = gameRepo.save(new Game(prod));

			return new ResponseEntity<>(new GameResponse(saveGame), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/createExpansion")
	public ResponseEntity<ExpansionResponse> postCreateExpansion(@RequestBody ExpansionRequest prod) {
		try {

			prod.setId(findNextAvailableId(EXPANSION));
			Expansion saveProduct = expansionRepo.save(new Expansion(prod));

			return new ResponseEntity<>(new ExpansionResponse(saveProduct), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// GET ENDPOINTS

	@GetMapping("/getExpansion/{expansionId}")
	public ResponseEntity<ExpansionResponse> getExpansion(@PathVariable Integer expansionId) {
		try {

			Optional<Expansion> saveProduct = expansionRepo.findById(expansionId);

			ExpansionResponse response = new ExpansionResponse(saveProduct.get());

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

	@GetMapping(value = "/getAllGames", produces = "application/json;charset=UTF-8")
	public ResponseEntity<List<GameResponse>> getAllGames() {
		try {

			List<Game> saveProduct = gameRepo.findAll();

			List<GameResponse> response = new ArrayList<>();
			saveProduct.stream().filter(Objects::nonNull).forEach(game -> response.add(new GameResponse(game)));

			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "/getAllProducts", produces = "application/json;charset=UTF-8")
	public ResponseEntity<List<ProductResponse>> getAllProducts() {
		try {

			List<Product> saveProduct = productRepo.findAll();

			List<ProductResponse> response = new ArrayList<>();
			saveProduct.stream().filter(Objects::nonNull)
					.forEach(product -> response.add(new ProductResponse(product)));

			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	private Integer findNextAvailableId(String table) {

		switch (table) {
		case GAME:
			Game g = gameRepo.findTopByOrderByIdDesc();
			return g.getId() + 1;
		case PRODUCT:
			Product p = productRepo.findTopByOrderByProductIdIdDesc();
			return p.getProductId().getId() + 1;
		case EXPANSION:
			Expansion e = expansionRepo.findTopByOrderByIdDesc();
			return e.getId() + 1;
		}

		return null;

	}

}
