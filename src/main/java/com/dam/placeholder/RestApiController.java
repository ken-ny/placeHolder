package com.dam.placeholder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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

@CrossOrigin(origins = "http://localhost:8080")
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

	private Date convertDate(String date) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");

		try {
			return formatter.parse(date);
		} catch (ParseException e) {
			return null;
		}
	}

}
