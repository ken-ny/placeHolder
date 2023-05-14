package com.dam.placeholder.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Offers {

	@Id
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "CARD_ID")
	private Card card;

	@ManyToOne
	@JoinColumn(name = "EXPANSION_ID")
	private Expansion expansion;

	private Integer quantity;

	private Double unitaryPrice;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public Expansion getExpansion() {
		return expansion;
	}

	public void setExpansion(Expansion expansion) {
		this.expansion = expansion;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getUnitaryPrice() {
		return unitaryPrice;
	}

	public void setUnitaryPrice(Double unitaryPrice) {
		this.unitaryPrice = unitaryPrice;
	}

	public Offers() {
		super();
	}

	public Offers(Integer id) {
		super();
		this.id = id;
	}

	public Offers(Integer id, Card card, Expansion expansion, Integer quantity, Double unitaryPrice) {
		super();
		this.id = id;
		this.card = card;
		this.expansion = expansion;
		this.quantity = quantity;
		this.unitaryPrice = unitaryPrice;
	}

	public Offers(Card card, Expansion expansion, Integer id) {
		this.card = card;
		this.id = id;
		this.expansion = expansion;
	}

	/**
	 * Incrementa la cantidad ofrecida en 1. En caso de que aún no haya ninguna
	 * cantidad, la inicializa a 0
	 * 
	 * @param quantity quantity to add
	 * @return
	 */
	public void increaseQuantity() {
		if (this.quantity == null) {
			this.quantity = 0;
		}
		this.quantity++;
	}

	/**
	 * Reduce la cantidad ofrecida en 1. En caso de que fuera a reducir la cantidad
	 * por debajo de 0 (o la cantidad original fuera 0), la deja en 0. En cualquier
	 * otro caso, lo reduce
	 * 
	 * @param quantity quantity to decrease
	 * @return
	 */
	public void decreaseQuantity() {
		if (this.quantity == 0 || (this.quantity - quantity) < 0) {
			this.quantity = 0;
		} else {
			this.quantity--;
		}

	}

}
