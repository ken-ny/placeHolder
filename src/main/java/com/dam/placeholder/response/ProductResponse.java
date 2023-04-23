package com.dam.placeholder.response;

import java.util.ArrayList;
import java.util.List;

import com.dam.placeholder.entity.Expansion;
import com.dam.placeholder.entity.Product;
import com.dam.placeholder.response.utils.ResponseUtils;
import com.dam.placeholder.serializer.ExpansionSerializer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ProductResponse {
	@JsonProperty
	private Integer id;
	@JsonProperty
	private String name;
	@JsonProperty
	private String rarity;
	@JsonProperty
	private Integer quantity;
	@JsonProperty
	private String image;
	@JsonSerialize(using = ExpansionSerializer.class)
	private List<ExpansionResponse> expansion;

	@JsonProperty
	private Error error;

	public ProductResponse(Error error) {
		super();
		this.error = error;
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

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public List<ExpansionResponse> getExpansion() {
		return expansion;
	}

	public void setExpansion(List<ExpansionResponse> expansion) {
		this.expansion = expansion;
	}

	public ProductResponse(Integer id, String name, String rarity, Integer quantity, String image,
			List<ExpansionResponse> expansion) {
		super();
		this.id = id;
		this.name = name;
		this.rarity = rarity;
		this.quantity = quantity;
		this.image = image;
		this.expansion = expansion;
	}

	public ProductResponse() {
		super();
	}

	public ProductResponse(Product product) {

		this.id = product.getId();
		this.image = product.getImage();
		this.name = product.getName();
		this.rarity = product.getRarity();
		this.quantity = product.getQuantity();
		List<Expansion> ex = new ArrayList<>(product.getExpansion());
		this.expansion = ResponseUtils.mapperExpansionToResponse(ex);

	}

	public Error getError() {
		return error;
	}

}
