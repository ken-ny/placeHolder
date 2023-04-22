package com.dam.placeholder.request;

import java.util.Date;
import java.util.List;

public class SalesRequest {

	private Integer id;
	private Date saleDate;
	private Double salePrice;
	private List<SaleDetailsRequest> details;

	public Date getSaleDate() {
		return saleDate;
	}

	public void setSaleDate(Date saleDate) {
		this.saleDate = saleDate;
	}

	public Double getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(Double salePrice) {
		this.salePrice = salePrice;
	}

	public List<SaleDetailsRequest> getDetails() {
		return details;
	}

	public void setDetails(List<SaleDetailsRequest> details) {
		this.details = details;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
