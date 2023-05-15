package com.dam.placeholder.cardmarket;

public class User {

	private Integer idUser;
	private String username;
	private String country;
	private Integer isCommercial;
	private Integer riskGroup;
	private Integer reputation;
	private Integer shipsFast;
	private Integer sellCount;
	private Boolean onVacation;
	private Integer idDisplayLanguage;
	private Name name;
	private Address address;

	public Integer getIdUser() {
		return idUser;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Integer getIsCommercial() {
		return isCommercial;
	}

	public void setIsCommercial(Integer isCommercial) {
		this.isCommercial = isCommercial;
	}

	public Integer getRiskGroup() {
		return riskGroup;
	}

	public void setRiskGroup(Integer riskGroup) {
		this.riskGroup = riskGroup;
	}

	public Integer getReputation() {
		return reputation;
	}

	public void setReputation(Integer reputation) {
		this.reputation = reputation;
	}

	public Integer getShipsFast() {
		return shipsFast;
	}

	public void setShipsFast(Integer shipsFast) {
		this.shipsFast = shipsFast;
	}

	public Integer getSellCount() {
		return sellCount;
	}

	public void setSellCount(Integer sellCount) {
		this.sellCount = sellCount;
	}

	public Boolean getOnVacation() {
		return onVacation;
	}

	public void setOnVacation(Boolean onVacation) {
		this.onVacation = onVacation;
	}

	public Integer getIdDisplayLanguage() {
		return idDisplayLanguage;
	}

	public void setIdDisplayLanguage(Integer idDisplayLanguage) {
		this.idDisplayLanguage = idDisplayLanguage;
	}

	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

}
