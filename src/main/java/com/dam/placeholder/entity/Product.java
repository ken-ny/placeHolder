package com.dam.placeholder.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
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

	@OneToMany(mappedBy = "productId")
	List<ProductExpansion> expansion;

	@OneToMany(mappedBy = "productId")
	List<Sales> productSales;

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

	public List<Sales> getProductSales() {
		return productSales;
	}

	public void setProductSales(List<Sales> productSales) {
		this.productSales = productSales;
	}

	public List<ProductExpansion> getExpansion() {
		return expansion;
	}

	public void setExpansion(List<ProductExpansion> expansion) {
		this.expansion = expansion;
	}

}
