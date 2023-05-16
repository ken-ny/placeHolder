package com.dam.placeholder.cardmarket;

import java.util.List;

public class Order {

	private Integer idOrder;
	private Boolean isBuyer;
	private User seller;
	private User buyer;
	private State state;
	private ShippingMethod shippingMethod;
	private String trackingNumber;
	private String temporaryEmail;
	private Boolean isPresale;
	private Address shippingAddress;
	private Integer articleCount;
	private Evaluation evaluation;
	private List<Article> article;
	private Double articleValue;
	private Double totalValue;
	private Integer idCurrency;
	private String currencyCode;
	private Long phoneNumber;

	public Integer getIdOrder() {
		return idOrder;
	}

	public void setIdOrder(Integer idOrder) {
		this.idOrder = idOrder;
	}

	public Boolean getIsBuyer() {
		return isBuyer;
	}

	public void setIsBuyer(Boolean isBuyer) {
		this.isBuyer = isBuyer;
	}

	public User getSeller() {
		return seller;
	}

	public void setSeller(User seller) {
		this.seller = seller;
	}

	public User getBuyer() {
		return buyer;
	}

	public void setBuyer(User buyer) {
		this.buyer = buyer;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public ShippingMethod getShippingMethod() {
		return shippingMethod;
	}

	public void setShippingMethod(ShippingMethod shippingMethod) {
		this.shippingMethod = shippingMethod;
	}

	public String getTrackingNumber() {
		return trackingNumber;
	}

	public void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}

	public String getTemporaryEmail() {
		return temporaryEmail;
	}

	public void setTemporaryEmail(String temporaryEmail) {
		this.temporaryEmail = temporaryEmail;
	}

	public Boolean getIsPresale() {
		return isPresale;
	}

	public void setIsPresale(Boolean isPresale) {
		this.isPresale = isPresale;
	}

	public Address getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(Address shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public Integer getArticleCount() {
		return articleCount;
	}

	public void setArticleCount(Integer articleCount) {
		this.articleCount = articleCount;
	}

	public Evaluation getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(Evaluation evaluation) {
		this.evaluation = evaluation;
	}

	public List<Article> getArticle() {
		return article;
	}

	public void setArticle(List<Article> article) {
		this.article = article;
	}

	public Double getArticleValue() {
		return articleValue;
	}

	public void setArticleValue(Double articleValue) {
		this.articleValue = articleValue;
	}

	public Double getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(Double totalValue) {
		this.totalValue = totalValue;
	}

	public Integer getIdCurrency() {
		return idCurrency;
	}

	public void setIdCurrency(Integer idCurrency) {
		this.idCurrency = idCurrency;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public Long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}
