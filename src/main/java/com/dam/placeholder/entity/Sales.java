package com.dam.placeholder.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "SALES")
public class Sales {

	@ManyToOne
	@JoinColumns({ @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "id"),
			@JoinColumn(name = "PRODUCT_NAME", referencedColumnName = "name"),
			@JoinColumn(name = "PRODUCT_RARITY", referencedColumnName = "rarity") })
	Product productId;

	@ManyToOne
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
