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
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SalesResponse {

	@JsonProperty
	private Integer id;
	@JsonProperty
	private Date saleDate;
	@JsonProperty
	private Double salePrice;
	@JsonSerialize(using = DetailsSerializer.class)
	private List<SaleDetailsResponse> details;

	@JsonProperty
	private Error error;

	public Error getError() {
		return error;
	}

	public void setError(Error error) {
		this.error = error;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getSaleDate() {
		return saleDate;
	}

	public void setSaleDate(Date sale_date) {
		this.saleDate = sale_date;
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

	public SalesResponse(Integer id, Date saleDate, Double salePrice, List<SaleDetailsResponse> details) {
		super();
		this.id = id;
		this.saleDate = saleDate;
		this.salePrice = salePrice;
		this.details = details;
	}

	public SalesResponse() {
		super();
	}

	public SalesResponse(Sales sale) {
		this.saleDate = sale.getSaleDate();
		this.salePrice = sale.getSalePrice();
		this.details = ResponseUtils.mapperDetailsToResponse(sale.getDetails());
		this.id = sale.getId();

	}

	public SalesResponse(Error error) {
		super();
		this.error = error;
	}

}
