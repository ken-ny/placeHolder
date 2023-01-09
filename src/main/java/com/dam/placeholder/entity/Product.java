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
	List<ProductSales> productSales;

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

	public List<ProductSales> getProductSales() {
		return productSales;
	}

	public void setProductSales(List<ProductSales> productSales) {
		this.productSales = productSales;
	}

}
