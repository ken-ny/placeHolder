package com.dam.placeholder.entity;

import java.io.Serializable;

import jakarta.persistence.Embeddable;

@Embeddable
public class ProductKey implements Serializable {

	private Integer id;
	private String name;
	private String rarity;

	public ProductKey(Integer id, String name, String rarity) {
		super();
		this.id = id;
		this.name = name;
		this.rarity = rarity;
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

}
