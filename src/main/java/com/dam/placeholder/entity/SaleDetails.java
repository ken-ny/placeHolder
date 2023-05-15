package com.dam.placeholder.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class SaleDetails {

	@Id
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "CARD_ID")
	private Card card;

	@ManyToOne
	@JoinColumn(name = "EXPANSION_ID")
	private Expansion expansion;

	@ManyToOne
	@JoinColumn(name = "SALE_ID")
	private Sales sale;

	private Integer quantity;

	private Double unitaryPrice;

	public Card getCard() {
		return card;
	}

	public void setCard(Card cardId) {
		this.card = cardId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Card getProduct() {
		return card;
	}

	public void setProduct(Card product) {
		this.card = product;
	}

	public Expansion getExpansion() {
		return expansion;
	}

	public void setExpansion(Expansion expansion) {
		this.expansion = expansion;
	}

	public Sales getSale() {
		return sale;
	}

	public void setSale(Sales sale) {
		this.sale = sale;
	}

	public Double getUnitaryPrice() {
		return unitaryPrice;
	}

	public void setUnitaryPrice(Double unitaryPrice) {
		this.unitaryPrice = unitaryPrice;
	}

	public SaleDetails(Integer id) {
		super();
		this.id = id;
	}

	public SaleDetails(Integer id, Card card, Expansion expansion, Sales sale, Integer quantity, Double unitaryPrice) {
		super();
		this.id = id;
		this.card = card;
		this.expansion = expansion;
		this.sale = sale;
		this.quantity = quantity;
		this.unitaryPrice = unitaryPrice;
	}

	public SaleDetails() {
		super();
	}

}
