package com.dam.placeholder.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class SaleDetails {

	@ManyToOne
	@JoinColumn(name = "PRODUCT_ID")
	Product product;

	@ManyToOne
	@JoinColumn(name = "EXPANSION_ID")
	Expansion expansion;

	Integer quantity;

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

	public SaleDetails(Product productId, Expansion expansionId, Integer quantity) {
		super();
		this.product = productId;
		this.expansion = expansionId;
		this.quantity = quantity;
	}

	public SaleDetails() {
		super();
	}

}
