package com.dam.placeholder.request;

import java.util.Date;
import java.util.List;

import com.dam.placeholder.entity.SaleDetails;

public class SalesRequest {

	Date sale_date;
	Double salePrice;
	List<SaleDetails> details;

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

	public List<SaleDetails> getDetails() {
		return details;
	}

	public void setDetails(List<SaleDetails> details) {
		this.details = details;
	}

}
