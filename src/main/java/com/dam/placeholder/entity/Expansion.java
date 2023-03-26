package com.dam.placeholder.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "EXPANSION")
public class Expansion {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer id;

	@Column(name = "NAME")
	String name;

	@Column(name = "ABBREVIATION")
	String abbreviation;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "RELEASE_DATE")
	Date release_date;

	@Column(name = "RELEASED")
	Boolean is_released;

	@ManyToOne
	@JoinColumn(name = "GAME_ID")
	Game game;

	@OneToMany(mappedBy = "expansionId")
	List<ProductExpansion> productExpansion;

	@OneToMany(mappedBy = "expansionId")
	List<ProductSales> productSales;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public Date getRelease_date() {
		return release_date;
	}

	public void setRelease_date(Date release_date) {
		this.release_date = release_date;
	}

	public Boolean getIs_released() {
		return is_released;
	}

	public void setIs_released(Boolean is_released) {
		this.is_released = is_released;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public List<ProductSales> getProductSales() {
		return productSales;
	}

	public void setProductSales(List<ProductSales> productSales) {
		this.productSales = productSales;
	}

	public List<ProductExpansion> getProductExpansion() {
		return productExpansion;
	}

	public void setProductExpansion(List<ProductExpansion> productExpansion) {
		this.productExpansion = productExpansion;
	}

}
