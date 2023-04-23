package com.dam.placeholder.response;

import com.dam.placeholder.entity.Offers;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class OffersResponse {

	@JsonProperty
	private Integer id;

	@JsonProperty
	private Integer quantity;

	@JsonProperty
	private Double unitaryPrice;

	@JsonProperty
	private ProductResponse product;
	@JsonProperty
	private ExpansionResponse expansion;

	@JsonProperty
	private Error error;

	public Error getError() {
		return error;
	}

	public void setError(Error error) {
		this.error = error;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	public Double getUnitaryPrice() {
		return unitaryPrice;
	}

	public void setUnitaryPrice(Double unitaryPrice) {
		this.unitaryPrice = unitaryPrice;
	}

	public OffersResponse(Integer id, Integer quantity, Double unitaryPrice, ProductResponse product,
			ExpansionResponse expansion) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.unitaryPrice = unitaryPrice;
		this.product = product;
		this.expansion = expansion;
	}

	public OffersResponse() {
		super();
	}

	public OffersResponse(Offers ex) {
		this.expansion = new ExpansionResponse(ex.getExpansion());
		this.product = new ProductResponse(ex.getProduct());
		this.quantity = ex.getQuantity();
		this.unitaryPrice = ex.getUnitaryPrice();
		this.id = ex.getId();
	}

	public OffersResponse(Error error) {
		super();
		this.error = error;
	}

}
