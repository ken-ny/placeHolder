package com.dam.placeholder.response;

import java.util.Objects;

import com.dam.placeholder.entity.SaleDetails;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SaleDetailsResponse {

	@JsonProperty
	private Integer id;
	@JsonProperty
	private ProductResponse product;
	@JsonProperty
	private ExpansionResponse expansion;

	@JsonProperty
	private Integer quantity;

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

	public SaleDetailsResponse(Integer id, ProductResponse product, ExpansionResponse expansion, Integer quantity) {
		super();
		this.id = id;
		this.product = product;
		this.expansion = expansion;
		this.quantity = quantity;
	}

	public SaleDetailsResponse() {
		super();
	}

	public SaleDetailsResponse(SaleDetails ex) {
		if (Objects.nonNull(ex.getExpansion())) {
			this.expansion = new ExpansionResponse(ex.getExpansion());
		}

		if (Objects.nonNull(ex.getProduct())) {
			this.product = new ProductResponse(ex.getProduct());
		}
		this.quantity = ex.getQuantity();
		this.id = ex.getId();
	}

	public SaleDetailsResponse(Error error) {
		super();
		this.error = error;
	}

}
