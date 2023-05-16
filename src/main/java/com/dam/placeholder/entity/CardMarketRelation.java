package com.dam.placeholder.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "CARDMARKET")
public class CardMarketRelation {
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "CARDMARKET_ID")
	private Integer idOrder;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "SALE_ID")
	private Sales sale;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdOrder() {
		return idOrder;
	}

	public void setIdOrder(Integer idOrder) {
		this.idOrder = idOrder;
	}

	public Sales getSale() {
		return sale;
	}

	public void setSale(Sales sale) {
		this.sale = sale;
	}

	public CardMarketRelation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CardMarketRelation(Integer id, Integer idOrder, Sales sale) {
		super();
		this.id = id;
		this.idOrder = idOrder;
		this.sale = sale;
	}

	public CardMarketRelation(Integer idOrder, Sales sale) {
		super();
		this.idOrder = idOrder;
		this.sale = sale;
	}

}
