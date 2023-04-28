package com.dam.placeholder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.dam.placeholder.entity.Expansion;
import com.dam.placeholder.entity.Game;
import com.dam.placeholder.entity.Offers;
import com.dam.placeholder.entity.Product;
import com.dam.placeholder.entity.SaleDetails;
import com.dam.placeholder.entity.Sales;

public class TestUtils {
	public List<Expansion> mockExpansionList(boolean withRelations) {

		List<Expansion> list = new ArrayList<>();

		if (withRelations) {
			list.add(new Expansion(1, "prueba1", "abreviation1", new Date(), true, mockGame(),
					Arrays.asList(mockProduct()), Arrays.asList(mockSales()), Arrays.asList(mockOffers())));
		} else {
			list.add(new Expansion(1, "prueba1", "abreviation1", new Date(), true, null, null, null, null));
		}
		list.add(new Expansion(2, "prueba2", "abreviation2", new Date(), true, null, null, null, null));

		return list;

	}

	public List<Product> mockProductList(boolean withRelations) {
		List<Product> list = new ArrayList<>();
		if (withRelations) {
			list.add(new Product(1, "prueba1", "RRR", 12, null, Set.of(mockExpansion()), Arrays.asList(mockSales()),
					Arrays.asList(mockOffers())));
		} else {
			list.add(new Product(1, "prueba1", "RRR", 12, null, null, null, null));
		}
		list.add(new Product(2, "prueba2", "RR", 12, null, null, null, null));
		return list;
	}

	public List<Game> mockGameList(boolean withRelations) {
		List<Game> list = new ArrayList<>();

		if (withRelations) {
			list.add(new Game(1, "prueba1", "abrv1", Arrays.asList(mockExpansion())));
		} else {
			list.add(new Game(1, "prueba1", "abrv1", null));
		}
		list.add(new Game(2, "prueba2", "abrv2", null));

		return list;
	}

	public List<Sales> mockSalesList(boolean withRelations) {
		List<Sales> list = new ArrayList<>();

		list.add(new Sales(1, new Date(), 20.00, Arrays.asList(mockSaleDetails())));
		list.add(new Sales(2, new Date(), 20.00, mockDetailsList(withRelations)));

		return list;
	}

	public List<SaleDetails> mockDetailsList(boolean withRelations) {
		List<SaleDetails> list = new ArrayList<>();

		if (withRelations) {
			list.add(new SaleDetails(1, mockProduct(), mockExpansion(), null, 2, 10.00));
		} else {
			list.add(new SaleDetails(1, null, null, null, 2, 10.00));
		}
		list.add(new SaleDetails(2, null, null, null, 2, 10.00));

		return list;
	}

	public List<Offers> mockOffersList(boolean withRelations) {
		List<Offers> list = new ArrayList<>();

		if (withRelations) {
			list.add(new Offers(1, mockProduct(), mockExpansion(), 15, 14.00));
		} else {
			list.add(new Offers(1, null, null, 15, 14.00));
		}
		list.add(new Offers(2, mockProduct(), mockExpansion(), 15, 14.00));

		return list;
	}

	public Product mockProduct() {
		Product prod = new Product();

		prod.setId(1);
		prod.setName("test");
		prod.setQuantity(15);
		prod.setRarity("C");

		return prod;
	}

	public Expansion mockExpansion() {
		Expansion exp = new Expansion();

		exp.setId(1);
		exp.setName("test");
		exp.setAbbreviation("abvr");
		exp.setIs_released(true);

		return exp;

	}

	public Game mockGame() {
		Game game = new Game();

		game.setId(1);
		game.setName("test");
		game.setAbbreviation("abvr");

		return game;
	}

	public Offers mockOffers() {
		Offers offer = new Offers();

		offer.setId(1);
		offer.setQuantity(18);
		offer.setUnitaryPrice(75.00);

		return offer;
	}

	public Sales mockSales() {
		Sales sale = new Sales();

		sale.setId(1);
		sale.setSaleDate(new Date());
		sale.setSalePrice(15.00);

		return sale;
	}

	public SaleDetails mockSaleDetails() {
		SaleDetails detail = new SaleDetails();

		detail.setId(1);
		detail.setQuantity(15);
		detail.setUnitaryPrice(20.00);

		return detail;
	}

}
