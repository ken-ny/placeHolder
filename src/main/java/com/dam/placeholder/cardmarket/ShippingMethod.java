package com.dam.placeholder.cardmarket;

public class ShippingMethod {

	private Integer idShippingMethod;
	private String name;
	private Double price;
	private Boolean isLetter;
	private Boolean isInsured;

	public Integer getIdShippingMethod() {
		return idShippingMethod;
	}

	public void setIdShippingMethod(Integer idShippingMethod) {
		this.idShippingMethod = idShippingMethod;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Boolean getIsLetter() {
		return isLetter;
	}

	public void setIsLetter(Boolean isLetter) {
		this.isLetter = isLetter;
	}

	public Boolean getIsInsured() {
		return isInsured;
	}

	public void setIsInsured(Boolean isInsured) {
		this.isInsured = isInsured;
	}

}
