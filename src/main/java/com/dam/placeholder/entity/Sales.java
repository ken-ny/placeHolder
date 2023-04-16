package com.dam.placeholder.entity;

import java.util.Date;
import java.util.List;

import com.dam.placeholder.request.SalesRequest;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "SALES")
public class Sales {

	@Id
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "SALE_DATE")
	Date sale_date;

	@Column(name = "SALE_PRICE")
	Double salePrice;

	@Embedded
	List<SaleDetails> details;

	public List<SaleDetails> getDetails() {
		return details;
	}

	public void setDetails(List<SaleDetails> details) {
		this.details = details;
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

	public Sales(Date sale_date, Double salePrice, List<SaleDetails> details) {
		super();
		this.sale_date = sale_date;
		this.salePrice = salePrice;
		this.details = details;
	}

	public Sales() {
		super();
	}

	public Sales(SalesRequest request) {
		this.details = request.getDetails();
		this.sale_date = request.getSale_date();
		this.salePrice = request.getSalePrice();
	}

}
