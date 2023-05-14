package com.dam.placeholder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.dam.placeholder.entity.Card;
import com.dam.placeholder.entity.Expansion;
import com.dam.placeholder.entity.Game;
import com.dam.placeholder.entity.Offers;
import com.dam.placeholder.entity.SaleDetails;
import com.dam.placeholder.entity.Sales;

public class TestUtils {
	private static final String DD_MM_YYYY = "dd-MM-yyyy";
	private static final String BROTH = "BROTH";
	private static final String LA_GUERRA_DE_LOS_HERMANOS = "La Guerra de los Hermanos";
	private static final String DBT08 = "DBT08";
	private static final String D_BOOSTER_SET_08_MINERVA_RISING = "D Booster Set 08: Minerva Rising";
	private static final String MAGIC_THE_GATHERING = "MAGIC THE GATHERING";
	private static final String MTG = "MTG";
	private static final String CARDFIGHT_VANGUARD = "CARDFIGHT VANGUARD";
	private static final String CFV = "CFV";
	private static final String ABRV2 = "abrv2";
	private static final String ABRV1 = "abrv1";
	private static final String PRUEBA2 = "prueba2";
	private static final String PRUEBA1 = "prueba1";
	private static final String C = "C";
	private static final String RRR = "RRR";
	private static final String IMAGE = "image";
	private static final String ABVR = "abvr";
	private static final String TEST = "test";

	public List<Expansion> mockExpansionList(boolean withRelations) {

		List<Expansion> list = new ArrayList<>();

		if (withRelations) {
			list.add(new Expansion(1, PRUEBA1, ABRV1, new Date(), true, mockGame(), Arrays.asList(mockProduct()),
					Arrays.asList(mockSales()), Arrays.asList(mockOffers())));
		} else {
			list.add(new Expansion(1, PRUEBA1, ABRV1, new Date(), true, null, null, null, null));
		}
		list.add(new Expansion(2, PRUEBA2, ABRV2, new Date(), true, null, null, null, null));

		return list;

	}

	public List<Card> mockProductList(boolean withRelations) {
		List<Card> list = new ArrayList<>();
		if (withRelations) {
			list.add(new Card(1, PRUEBA1, RRR, 12, null, Set.of(mockExpansion()), Arrays.asList(mockSales()),
					Arrays.asList(mockOffers())));
		} else {
			list.add(new Card(1, PRUEBA1, RRR, 12, null, null, null, null));
		}
		list.add(new Card(2, PRUEBA2, "RR", 12, null, null, null, null));
		return list;
	}

	public List<Game> mockGameList(boolean withRelations) {
		List<Game> list = new ArrayList<>();

		if (withRelations) {
			list.add(new Game(1, PRUEBA1, ABRV1, Arrays.asList(mockExpansion())));
		} else {
			list.add(new Game(1, PRUEBA1, ABRV1, null));
		}
		list.add(new Game(2, PRUEBA2, ABRV2, null));

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

	public Card mockProduct() {
		Card prod = new Card();

		prod.setId(1);
		prod.setName(TEST);
		prod.setQuantity(15);
		prod.setRarity(C);

		return prod;
	}

	public Expansion mockExpansion() {
		Expansion exp = new Expansion();

		exp.setId(1);
		exp.setName(TEST);
		exp.setAbbreviation(ABVR);
		exp.setIs_released(true);

		return exp;

	}

	public Game mockGame() {
		Game game = new Game();

		game.setId(1);
		game.setName(TEST);
		game.setAbbreviation(ABVR);

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

	/**
	 * @return
	 * @throws ParseException
	 */
	private Date convertDate(String date) {
		try {
			return new SimpleDateFormat(DD_MM_YYYY).parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

}
