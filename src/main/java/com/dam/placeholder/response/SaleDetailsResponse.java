package com.dam.placeholder.response;

import com.dam.placeholder.entity.Expansion;
import com.dam.placeholder.entity.Product;
import com.dam.placeholder.entity.SaleDetails;
import com.dam.placeholder.serializer.ExpansionSerializer;
import com.dam.placeholder.serializer.ProductSerializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import jakarta.persistence.Embeddable;

@Embeddable
public class SaleDetailsResponse {

	@JsonSerialize(using = ProductSerializer.class)
	Product product;
	@JsonSerialize(using = ExpansionSerializer.class)
	Expansion expansion;
	@JsonProperty
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

	public SaleDetailsResponse(Product productId, Expansion expansionId, Integer quantity) {
		super();
		this.product = productId;
		this.expansion = expansionId;
		this.quantity = quantity;
	}

	public SaleDetailsResponse() {
		super();
	}

	public SaleDetailsResponse(SaleDetails ex) {
		this.expansion = ex.getExpansionId();
		this.product = ex.getProductId();
		this.quantity = ex.getQuantity();
	}

}
