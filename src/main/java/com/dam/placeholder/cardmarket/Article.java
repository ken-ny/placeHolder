package com.dam.placeholder.cardmarket;

public class Article {

	private Integer idArticle;
	private Integer idProduct;
	private Language language;
	private String comments;
	private Double price;
	private Integer count;
	private Product product;
	private String condition;
	private Boolean isFoil;
	private Boolean isSigned;
	private Boolean isPlayset;

	public Integer getIdArticle() {
		return idArticle;
	}

	public void setIdArticle(Integer idArticle) {
		this.idArticle = idArticle;
	}

	public Integer getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(Integer idProduct) {
		this.idProduct = idProduct;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public Boolean getIsFoil() {
		return isFoil;
	}

	public void setIsFoil(Boolean isFoil) {
		this.isFoil = isFoil;
	}

	public Boolean getIsSigned() {
		return isSigned;
	}

	public void setIsSigned(Boolean isSigned) {
		this.isSigned = isSigned;
	}

	public Boolean getIsPlayset() {
		return isPlayset;
	}

	public void setIsPlayset(Boolean isPlayset) {
		this.isPlayset = isPlayset;
	}

}
