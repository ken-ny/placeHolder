package com.dam.placeholder.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.util.CollectionUtils;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "CARD")
public class Card {

	@Id
	private Integer id;
	@Column(name = "NAME")
	private String name;
	@Column(name = "RARITY")
	private String rarity;
	@Column(name = "IMAGE")
	private String image;

	@ManyToMany
	@JoinTable(name = "CARD_EXPANSION", joinColumns = @JoinColumn(name = "card_id"), inverseJoinColumns = @JoinColumn(name = "expansion_id"))
	Set<Expansion> expansion;

	@OneToMany(mappedBy = "card", targetEntity = SaleDetails.class, cascade = CascadeType.REMOVE)
	List<Sales> cardSales;

	@OneToMany(mappedBy = "card", cascade = CascadeType.REMOVE)
	List<Offers> offers;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRarity() {
		return rarity;
	}

	public void setRarity(String rarity) {
		this.rarity = rarity;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public List<Sales> getCardSales() {
		return cardSales;
	}

	public void setCardSales(List<Sales> cardSales) {
		this.cardSales = cardSales;
	}

	public Set<Expansion> getExpansion() {
		return expansion;
	}

	public void addExpansion(Expansion expansion) {
		if (CollectionUtils.isEmpty(this.expansion)) {
			this.expansion = new HashSet<>();
		}
		this.expansion.add(expansion);
	}

	public void addAllExpansions(Set<Expansion> expansions) {
		if (CollectionUtils.isEmpty(this.expansion)) {
			this.expansion = new HashSet<>();
		}
		this.expansion.addAll(expansions);
	}

	public void addSales(Sales sale) {
		if (CollectionUtils.isEmpty(this.cardSales)) {
			this.cardSales = new ArrayList<>();
		}
		this.cardSales.add(sale);
	}

	public void addOffers(Offers offer) {
		if (CollectionUtils.isEmpty(this.offers)) {
			this.offers = new ArrayList<>();
		}
		this.offers.add(offer);
	}

	public void setExpansion(Set<Expansion> expansion) {
		this.expansion = expansion;
	}

	public List<Offers> getOffers() {
		return offers;
	}

	public void setOffers(List<Offers> offers) {
		this.offers = offers;
	}

	public Card(Integer id) {
		super();
		this.id = id;
	}

	public Card(Integer id, String name, String rarity, String image, Set<Expansion> expansion, List<Sales> cardSales,
			List<Offers> offers) {
		super();
		this.id = id;
		this.name = name;
		this.rarity = rarity;
		this.image = image;
		this.expansion = expansion;
		this.cardSales = cardSales;
		this.offers = offers;
	}

	public Card(Integer id, String name, String rarity, String image, Expansion expansion, Sales cardSales,
			Offers offers) {
		super();
		this.id = id;
		this.name = name;
		this.rarity = rarity;
		this.image = image;
		addExpansion(expansion);
		addSales(cardSales);
		addOffers(offers);
	}

	public Card(int id, String name, String rarity, Expansion expansion) {
		super();
		this.id = id;
		this.name = name;
		this.rarity = rarity;
		addExpansion(expansion);
	}

	public Card() {
		super();
	}

}
