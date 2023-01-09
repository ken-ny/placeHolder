package com.dam.placeholder.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

public class ProductSales {

	@ManyToOne
	@MapsId("productId")
	@JoinColumn(name = "PRODUCT_ID")
	Product productId;

	@ManyToOne
	@MapsId("id")
	@JoinColumn(name = "EXPANSION_ID")
	Expansion expansionId;

	@Id
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "SALE_DATE")
	Date sale_date;

	@Column(name = "SALE_PRICE")
	Double salePrice;

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

	public Date getSale_date() {
		return sale_date;
	}

	public void setSale_date(Date sale_date) {
		this.sale_date = sale_date;
	}

	public Double getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(Double salePrice) {
		this.salePrice = salePrice;
	}

}
