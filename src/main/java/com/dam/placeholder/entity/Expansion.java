package com.dam.placeholder.entity;

import java.util.Date;
import java.util.List;

import com.dam.placeholder.request.ExpansionRequest;
import com.dam.placeholder.response.utils.ResponseUtils;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
	List<Sales> productSales;

	public Expansion(ExpansionRequest prod) {
		this.abbreviation = prod.getAbbreviation();
		this.id = prod.getId();
		this.is_released = prod.getIs_released();
		this.name = prod.getName();
		this.release_date = ResponseUtils.convertStringToDate(prod.getRelease_date());
		this.game = prod.getGame();
	}

	public Expansion() {
		super();
	}

	public Expansion(Integer id, String name, String abbreviation, Date release_date, Boolean is_released, Game game,
			List<ProductExpansion> productExpansion, List<Sales> productSales) {
		super();
		this.id = id;
		this.name = name;
		this.abbreviation = abbreviation;
		this.release_date = release_date;
		this.is_released = is_released;
		this.game = game;
		this.productExpansion = productExpansion;
		this.productSales = productSales;
	}

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

	public List<Sales> getProductSales() {
		return productSales;
	}

	public void setProductSales(List<Sales> productSales) {
		this.productSales = productSales;
	}

	public List<ProductExpansion> getProductExpansion() {
		return productExpansion;
	}

	public void setProductExpansion(List<ProductExpansion> productExpansion) {
		this.productExpansion = productExpansion;
	}

}
