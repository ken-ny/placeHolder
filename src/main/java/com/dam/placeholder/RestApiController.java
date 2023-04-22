package com.dam.placeholder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dam.placeholder.entity.Expansion;
import com.dam.placeholder.entity.Game;
import com.dam.placeholder.entity.Product;
import com.dam.placeholder.entity.SaleDetails;
import com.dam.placeholder.entity.Sales;
import com.dam.placeholder.repo.ExpansionRepository;
import com.dam.placeholder.repo.GameRepository;
import com.dam.placeholder.repo.ProductRepository;
import com.dam.placeholder.repo.SaleDetailsRepository;
import com.dam.placeholder.repo.SalesRepository;
import com.dam.placeholder.request.ExpansionRequest;
import com.dam.placeholder.request.GameRequest;
import com.dam.placeholder.request.ProductRequest;
import com.dam.placeholder.request.SaleDetailsRequest;
import com.dam.placeholder.request.SalesRequest;
import com.dam.placeholder.response.ExpansionResponse;
import com.dam.placeholder.response.GameResponse;
import com.dam.placeholder.response.ProductResponse;
import com.dam.placeholder.response.SalesResponse;

@RestController
@RequestMapping("/placeHolder")
public class RestApiController {

	private static final String EXPANSION = "E";

	private static final String PRODUCT = "P";

	private static final String GAME = "G";

	private static final String SALES = "S";

	private static final String SALE_DETAILS = "SD";

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

	// POST ENDPOINTS

	@PostMapping("/createProduct")
	public ResponseEntity<ProductResponse> postCreateProduct(@RequestBody ProductRequest product) {
		try {

			generateProductId(product);

			Product saveProduct = productRepo.save(new Product(product));

			return new ResponseEntity<>(new ProductResponse(saveProduct), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/createGame")
	public ResponseEntity<GameResponse> postCreateGame(@RequestBody GameRequest game) {
		try {
			generateGameId(game);

			Game savedGame = gameRepo.save(new Game(game));

			return new ResponseEntity<>(new GameResponse(savedGame), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/createExpansion")
	public ResponseEntity<ExpansionResponse> postCreateExpansion(@RequestBody ExpansionRequest expansion) {
		try {

			generateExpansionId(expansion);

			Expansion savedExpansion = expansionRepo.save(new Expansion(expansion));

			return new ResponseEntity<>(new ExpansionResponse(savedExpansion), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/createSale")
	public ResponseEntity<SalesResponse> postCreateExpansion(@RequestBody SalesRequest sale) {
		try {
			generateSaleId(sale);

			// crea el objeto sales sin relaciones
			Sales newSale = salesRepo.saveAndFlush(new Sales(sale));
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

	// GET ENDPOINTS

	@GetMapping("/getExpansion/{expansionId}")
	public ResponseEntity<ExpansionResponse> getExpansion(@PathVariable Integer expansionId) {
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
	public ResponseEntity<GameResponse> getGame(@PathVariable Integer gameId) {
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
	public ResponseEntity<ProductResponse> getProduct(@PathVariable Integer productId) {
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
	private void generateProductId(ProductRequest product) {
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
	private void generateGameId(GameRequest game) {
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
	private void generateExpansionId(ExpansionRequest expansion) {
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
	private void generateSaleId(SalesRequest sale) {
		if (sale.getId() == null) {
			Sales foundSale = salesRepo.findBySaleDateAndSalePrice(sale.getSaleDate(), sale.getSalePrice());
			if (Objects.nonNull(foundSale)) {
				sale.setId(foundSale.getId());
			} else {
				sale.setId(findNextAvailableId(SALES));
			}
		}
	}

}
