package com.dam.placeholder.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.CollectionUtils;

import com.dam.placeholder.request.SalesRequest;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "SALES")
public class Sales {

	@Id
	@Column(name = "ID")
	private Integer id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "SALE_DATE")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date saleDate;

	@Column(name = "SALE_PRICE")
	private Double salePrice;

	@OneToMany(mappedBy = "sale", cascade = CascadeType.REMOVE)
	private List<SaleDetails> details;

	public List<SaleDetails> getDetails() {
		return details;
	}

	public void setDetails(List<SaleDetails> details) {
		this.details = details;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void removeDetail(SaleDetails detail) {
		this.details.remove(detail);
	}

	public void addDetail(SaleDetails detail) {
		if (CollectionUtils.isEmpty(this.details)) {
			this.details = new ArrayList<>();
		}
		this.details.add(detail);
	}

	public Sales(Integer id, Date saleDate, Double salePrice, List<SaleDetails> details) {
		super();
		this.id = id;
		this.saleDate = saleDate;
		this.salePrice = salePrice;
		this.details = details;
	}

	public Sales() {
		super();
	}

	public Sales(SalesRequest request) {
		this.id = request.getId();
		this.details = new ArrayList<>();
		this.saleDate = request.getSaleDate();
		this.salePrice = request.getSalePrice();
	}

}
