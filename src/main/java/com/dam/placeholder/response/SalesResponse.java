package com.dam.placeholder.response;

import java.util.Date;
import java.util.List;

import com.dam.placeholder.entity.Sales;
import com.dam.placeholder.response.utils.ResponseUtils;
import com.dam.placeholder.serializer.DetailsSerializer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SalesResponse {

	@JsonProperty
	Date sale_date;
	@JsonProperty
	Double salePrice;
	@JsonSerialize(using = DetailsSerializer.class)
	List<SaleDetailsResponse> details;

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

	public List<SaleDetailsResponse> getDetails() {
		return details;
	}

	public void setDetails(List<SaleDetailsResponse> details) {
		this.details = details;
	}

	public SalesResponse(Date sale_date, Double salePrice, List<SaleDetailsResponse> details) {
		super();
		this.sale_date = sale_date;
		this.salePrice = salePrice;
		this.details = details;
	}

	public SalesResponse() {
		super();
	}

	public SalesResponse(Sales sale) {
		this.sale_date = sale.getSale_date();
		this.salePrice = sale.getSalePrice();
		this.details = ResponseUtils.mapperDetailsToResponse(sale.getDetails());

	}

}
