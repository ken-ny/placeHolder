package com.dam.placeholder.response;

import com.dam.placeholder.entity.SaleDetails;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SaleDetailsResponse {
	@JsonProperty
	ProductResponse product;
	@JsonProperty
	ExpansionResponse expansion;

	@JsonProperty
	Integer quantity;

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public ProductResponse getProduct() {
		return product;
	}

	public void setProduct(ProductResponse product) {
		this.product = product;
	}

	public ExpansionResponse getExpansion() {
		return expansion;
	}

	public void setExpansion(ExpansionResponse expansion) {
		this.expansion = expansion;
	}

	public SaleDetailsResponse(ProductResponse product, ExpansionResponse expansion, Integer quantity) {
		super();
		this.product = product;
		this.expansion = expansion;
		this.quantity = quantity;
	}

	public SaleDetailsResponse() {
		super();
	}

	public SaleDetailsResponse(SaleDetails ex) {
		this.expansion = new ExpansionResponse(ex.getExpansion());
		this.product = new ProductResponse(ex.getProduct());
		this.quantity = ex.getQuantity();
	}

}
