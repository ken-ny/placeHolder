package com.dam.placeholder.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "PRODUCT")
public class Product {

	@EmbeddedId
	ProductKey productId;
	@Column(name = "QUANTITY")
	Integer quantity;
	@Column(name = "IMAGE")
	Long image;
	@ManyToMany
	@JoinTable(name = "product_expansion", joinColumns = @JoinColumn(name = "PRODUCT_ID"), inverseJoinColumns = @JoinColumn(name = "EXPANSION_ID"))
	List<Expansion> expansionId;

	public ProductKey getProductId() {
		return productId;
	}

	public void setProductId(ProductKey productId) {
		this.productId = productId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Long getImage() {
		return image;
	}

	public void setImage(Long image) {
		this.image = image;
	}

	public List<Expansion> getExpansionId() {
		return expansionId;
	}

	public void setExpansionId(List<Expansion> expansionId) {
		this.expansionId = expansionId;
	}

}
