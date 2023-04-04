package com.dam.placeholder.entity;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;

@Embeddable
public class ProductKey implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private String rarity;

	public ProductKey(Integer id, String name, String rarity) {
		super();
		this.id = id;
		this.name = name;
		this.rarity = rarity;
	}

	public ProductKey() {
		super();
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, rarity);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductKey other = (ProductKey) obj;
		return Objects.equals(id, other.id) && Objects.equals(name, other.name) && Objects.equals(rarity, other.rarity);
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
