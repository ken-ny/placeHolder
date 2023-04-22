package com.dam.placeholder.entity;

import com.dam.placeholder.request.SaleDetailsRequest;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class SaleDetails {

	@Id
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "PRODUCT_ID")
	private Product product;

	@ManyToOne
	@JoinColumn(name = "EXPANSION_ID")
	private Expansion expansion;

	@ManyToOne
	@JoinColumn(name = "SALE_ID")
	private Sales sale;

	private Integer quantity;

	private Double unitaryPrice;

	public Product getProductId() {
		return product;
	}

	public void setProductId(Product productId) {
		this.product = productId;
	}

	public Expansion getExpansionId() {
		return expansion;
	}

	public void setExpansionId(Expansion expansionId) {
		this.expansion = expansionId;
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

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
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

	public SaleDetails(Integer id, Product product, Expansion expansion, Sales sale, Integer quantity,
			Double unitaryPrice) {
		super();
		this.id = id;
		this.product = product;
		this.expansion = expansion;
		this.sale = sale;
		this.quantity = quantity;
		this.unitaryPrice = unitaryPrice;
	}

	public SaleDetails() {
		super();
	}

	public SaleDetails(SaleDetailsRequest details) {
		this.id = details.getId();
		this.product = details.getProduct();
		this.expansion = details.getExpansion();
		this.sale = details.getSale();
		this.quantity = details.getQuantity();
		this.unitaryPrice = details.getUnitaryPrice();
	}

}
