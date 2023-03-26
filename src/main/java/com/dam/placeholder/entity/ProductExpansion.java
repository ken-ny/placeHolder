package com.dam.placeholder.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "PRODUCT_EXPANSION")
public class ProductExpansion {

	@Id
	@ManyToOne
	@JoinColumns({ @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "id"),
			@JoinColumn(name = "PRODUCT_NAME", referencedColumnName = "name"),
			@JoinColumn(name = "PRODUCT_RARITY", referencedColumnName = "rarity") })
	Product productId;

	@ManyToOne
	@JoinColumn(name = "EXPANSION_ID")
	Expansion expansionId;

	public Product getProductId() {
		return productId;
	}

	public void setProductId(Product productId) {
		this.productId = productId;
	}

	public Expansion getExpansionId() {
		return expansionId;
	}

	public void setExpansionId(Expansion expansionId) {
		this.expansionId = expansionId;
	}

}
