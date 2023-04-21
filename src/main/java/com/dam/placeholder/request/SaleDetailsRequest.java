package com.dam.placeholder.request;

import com.dam.placeholder.entity.Expansion;
import com.dam.placeholder.entity.Product;
import com.dam.placeholder.entity.Sales;

public class SaleDetailsRequest {

	Integer id;
	Product product;
	Expansion expansion;
	Integer quantity;
	Sales sale;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Expansion getExpansion() {
		return expansion;
	}

	public void setExpansion(Expansion expansion) {
		this.expansion = expansion;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Sales getSale() {
		return sale;
	}

	public void setSale(Sales sale) {
		this.sale = sale;
	}

}
