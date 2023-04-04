package com.dam.placeholder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.dam.placeholder.response.GameResponse;

@RestController
@RequestMapping("/placeHolder")
public class RestApiController {

	@Autowired
	ProductRepository productRepo;

	@Autowired
	ExpansionRepository expansionRepo;

	@Autowired
	GameRepository gameRepo;

	@PostMapping("/createProduct")
	public ResponseEntity<List<Product>> postCreateProduct(@RequestBody Product prod) {
		try {

			Product saveProduct = productRepo.save(prod);

			List<Product> listProducts = new ArrayList<Product>();
			listProducts.add(saveProduct);
			return new ResponseEntity<>(listProducts, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/createGame")
	public ResponseEntity<List<Game>> postCreateGame(@RequestBody Game prod) {
		try {

			Game saveGame = gameRepo.save(prod);

			List<Game> listGame = new ArrayList<Game>();
			listGame.add(saveGame);
			return new ResponseEntity<>(listGame, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/createExpansion")
	public ResponseEntity<List<Expansion>> postCreateExpansion(@RequestBody Expansion prod) {
		try {

			Expansion saveProduct = expansionRepo.save(prod);

			List<Expansion> listExpansion = new ArrayList<Expansion>();
			listExpansion.add(saveProduct);
			return new ResponseEntity<>(listExpansion, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/getExpansion/{id}")
	public ResponseEntity<Expansion> getExpansion(@PathVariable Integer expansionId) {
		try {

			Optional<Expansion> saveProduct = expansionRepo.findById(expansionId);

			return new ResponseEntity<>(saveProduct.get(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/getAllExpansions")
	public ResponseEntity<List<Expansion>> getAllExpansion() {
		try {

			List<Expansion> saveProduct = expansionRepo.findAll();

			return new ResponseEntity<>(saveProduct, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// funciona
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

	private Date convertDate(String date) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");

		try {
			return formatter.parse(date);
		} catch (ParseException e) {
			return null;
		}
	}

}
