package com.dam.placeholder.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.CollectionUtils;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date saleDate;

	@Column(name = "SALE_PRICE")
	private Double salePrice;

	@OneToMany(mappedBy = "sale", cascade = CascadeType.REMOVE)
	private List<SaleDetails> details;

	@OneToOne(mappedBy = "sale")
	private CardMarketRelation cardMarketId;

	@Column(name = "STATUS")
	private String status;

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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public CardMarketRelation getCardMarketId() {
		return cardMarketId;
	}

	public void setCardMarketId(CardMarketRelation cardMarketId) {
		this.cardMarketId = cardMarketId;
	}

	public void addDetail(SaleDetails detail) {
		if (CollectionUtils.isEmpty(this.details)) {
			this.details = new ArrayList<>();
		}
		this.details.add(detail);
	}

	public Sales(Integer id, Date saleDate, Double salePrice, List<SaleDetails> details, String status) {
		super();
		this.id = id;
		this.saleDate = saleDate;
		this.salePrice = salePrice;
		this.details = details;
		this.status = status;
	}

	public Sales(Integer id) {
		super();
		this.id = id;
	}

	public Sales() {
		super();
	}

}
