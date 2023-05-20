package com.dam.placeholder.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import com.dam.placeholder.cardmarket.Address;
import com.dam.placeholder.cardmarket.Article;
import com.dam.placeholder.cardmarket.Evaluation;
import com.dam.placeholder.cardmarket.Language;
import com.dam.placeholder.cardmarket.Name;
import com.dam.placeholder.cardmarket.Order;
import com.dam.placeholder.cardmarket.Product;
import com.dam.placeholder.cardmarket.ShippingMethod;
import com.dam.placeholder.cardmarket.State;
import com.dam.placeholder.cardmarket.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Clase de utilidad para pruebas que genera un número X de objetos Order con
 * datos aleatorios en el archivo cardmarket.json en el resources. X viene
 * determinado por el usuario por el terminal.
 * 
 * @author kenni
 *
 */
public class LoboDeCardMarket {

	private static RandomLists cardExpansionList = new RandomLists();
	private static Double totalValue;

	List<Integer> usedId = new ArrayList<>();

	public static void main(String[] args) throws IOException {

		System.out.println("Por favor, especifica la cantidad de datos a generar. (Numeros positivos a ser posible): ");
		Integer numberOfCases = new Scanner(System.in).nextInt();

		List<Order> orderList = new ArrayList<>();

		for (int i = 1; i <= numberOfCases; i++) {
			orderList.add(generateRandomOrder(i));
		}
		generateFile(orderList);

	}

	/**
	 * Este metodo lee la lista de objetos, la transforma a un String json y lo
	 * guarda en el archivo cardMarket en el resource. En caso de moverse el
	 * proyecto, se debe cambiar la ruta de manera acorde
	 * 
	 * @param orderList
	 * @throws JsonProcessingException
	 * @throws IOException
	 */
	private static void generateFile(List<Order> orderList) throws JsonProcessingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(orderList);

		// cambiar esta ruta en caso de mover el proyecto
		Path path = Paths.get("D:\\wrk\\proyectoFinModulo\\placeholder\\src\\main\\resources\\cardmarket.json");
		byte[] strToBytes = json.getBytes();

		Files.write(path, strToBytes);
	}

	private static Order generateRandomOrder(int i) {
		Order order = new Order();
		order.setIdOrder(i);
		order.setIsBuyer(false);
		order.setSeller(generateStandardUser());
		order.setBuyer(generateStandardUser());
		order.setState(generateRandomState());
		order.setShippingMethod(generateStandardShippingMethod());
		order.setTrackingNumber("");
		order.setTemporaryEmail("");
		order.setShippingAddress(generateStandardShippingAddress());
		Integer numberOfArticles = ThreadLocalRandom.current().nextInt(1, 10);
		order.setArticleCount(numberOfArticles);
		order.setEvaluation(generateStandardEvaluation());
		order.setArticle(generateRandomArticle(numberOfArticles));
		order.setArticleValue(totalValue);
		order.setTotalValue(totalValue);
		order.setIdCurrency(1);
		order.setCurrencyCode("EUR");
		order.setPhoneNumber(ThreadLocalRandom.current().nextLong());

		return order;
	}

	private static State generateRandomState() {
		State state = new State();

		state.setState(cardExpansionList.getRandomState());
		String randomDate = generateRandomDate();
		state.setDateBought(randomDate);
		state.setDatePaid(randomDate);
		state.setDateSent(randomDate);
		state.setDateReceived(randomDate);
		return state;
	}

	private static String generateRandomDate() {
		LocalDate startInclusive = LocalDate.of(2019, 1, 1);
		LocalDate endExclusive = LocalDate.now();

		long startEpochDay = startInclusive.toEpochDay();
		long endEpochDay = endExclusive.toEpochDay();
		long randomDay = ThreadLocalRandom.current().nextLong(startEpochDay, endEpochDay);

		return LocalDate.ofEpochDay(randomDay).toString();
	}

	private static User generateStandardUser() {
		User user = new User();

		user.setIdUser(1);
		user.setUsername("Mini");
		user.setCountry("IT");
		user.setIsCommercial(0);
		user.setRiskGroup(0);
		user.setReputation(0);
		user.setShipsFast(0);
		user.setSellCount(0);
		user.setIdDisplayLanguage(5);
		user.setName(generateStandardName());
		user.setAddress(generateStandardShippingAddress());

		return user;
	}

	private static Name generateStandardName() {
		Name name = new Name();

		name.setFirstName("Mario");
		name.setLastName("Rossi");

		return name;
	}

	private static ShippingMethod generateStandardShippingMethod() {
		ShippingMethod method = new ShippingMethod();

		method.setIdShippingMethod(20);
		method.setName("Standardbrief International");
		method.setPrice(1.0);

		return method;
	}

	private static Address generateStandardShippingAddress() {
		Address address = new Address();

		address.setName("Mario Rossi");
		address.setExtra("");
		address.setStreet("46 de Gasperi");
		address.setZip("10000");
		address.setCity("Roma");
		address.setCountry("IT");

		return address;
	}

	private static Evaluation generateStandardEvaluation() {
		Evaluation eval = new Evaluation();

		eval.setComment("not foil, but alternate art and wrong edition");
		eval.setSpeed(1);
		eval.setEvaluationGrade(1);
		eval.setItemDescription(4);
		eval.setPackaging(1);
		eval.setComplaint(Arrays.asList("wrongEd"));

		return eval;
	}

	private static List<Article> generateRandomArticle(Integer numberOfArticles) {
		totalValue = 0.0;
		List<Article> articleList = new ArrayList<>();
		for (int i = 1; i <= numberOfArticles; i++) {
			Article article = new Article();

			article.setIdArticle(ThreadLocalRandom.current().nextInt(1));
			article.setIdProduct(ThreadLocalRandom.current().nextInt(1));
			article.setLanguage(generateStandardLanguage());
			article.setComments("");

			Double price = ThreadLocalRandom.current().nextDouble(1.0, 1000.0);
			totalValue += price;
			article.setPrice(price);

			article.setCount(1);
			article.setProduct(generateRandomProduct());
			article.setCondition(cardExpansionList.getRandomCondition());

			articleList.add(article);
		}

		return articleList;

	}

	private static Product generateRandomProduct() {
		Product product = new Product();

		product.setName(cardExpansionList.getRandomCard());
		product.setImage(".\\/img\\/items\\/3\\/War_of_the_Ancients\\/despair_of_winter.jpg");
		product.setExpansion(cardExpansionList.getRandomExpansion());
		product.setExpIcon(204);
		product.setRarity(cardExpansionList.getRandomRarity());

		return product;

	}

	private static Language generateStandardLanguage() {
		Language lang = new Language();

		lang.setIdLanguage(1);
		lang.setLanguageName("English");

		return lang;
	}

}
