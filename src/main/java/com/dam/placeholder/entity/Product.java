package com.dam.placeholder.entity;

import java.util.List;

import com.dam.placeholder.request.ProductRequest;

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
	String image;

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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
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

	public Product(ProductRequest prod) {
		this.productId = new ProductKey(prod.getId(), prod.getName(), prod.getRarity());
		this.image = prod.getImage();
		this.quantity = prod.getQuantity();

	}

	public Product(ProductKey productId, Integer quantity, String image, List<ProductExpansion> expansion,
			List<Sales> productSales) {
		super();
		this.productId = productId;
		this.quantity = quantity;
		this.image = image;
		this.expansion = expansion;
		this.productSales = productSales;
	}

	public Product() {
		super();
	}

}
