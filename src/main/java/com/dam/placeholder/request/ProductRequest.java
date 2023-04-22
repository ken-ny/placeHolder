package com.dam.placeholder.request;

import java.util.List;

import com.dam.placeholder.entity.Expansion;

public class ProductRequest {
	private Integer id;
	private String name;
	private String rarity;
	private Integer quantity;
	private String image;
	private List<Expansion> expansions;

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

	public List<Expansion> getExpansions() {
		return expansions;
	}

	public void setExpansions(List<Expansion> expansions) {
		this.expansions = expansions;
	}

}
