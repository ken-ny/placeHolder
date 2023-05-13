package com.dam.placeholder.entity;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.dam.placeholder.request.ExpansionRequest;
import com.dam.placeholder.response.utils.ResponseUtils;

import io.micrometer.common.util.StringUtils;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
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
	private Integer id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "ABBREVIATION")
	private String abbreviation;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "RELEASE_DATE")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date release_date;

	@Column(name = "RELEASED")
	private Boolean is_released;

	@ManyToOne
	@JoinColumn(name = "GAME_ID")
	private Game game;

	@ManyToMany(mappedBy = "expansion", cascade = CascadeType.REMOVE)
	private List<Product> productExpansion;

	@OneToMany(mappedBy = "expansion", targetEntity = SaleDetails.class, cascade = CascadeType.REMOVE)
	private List<Sales> productSales;

	@OneToMany(mappedBy = "expansion", cascade = CascadeType.REMOVE)
	private List<Offers> offers;

	public Expansion(ExpansionRequest prod) {
		this.abbreviation = prod.getAbbreviation();
		this.id = prod.getId();
		this.is_released = prod.getIs_released();
		this.name = prod.getName();
		if (StringUtils.isNotBlank(prod.getRelease_date())) {
			this.release_date = ResponseUtils.convertStringToDate(prod.getRelease_date());
		}
		this.game = prod.getGame();
	}

	public Expansion() {
		super();
	}

	public Expansion(Integer id, String name, String abbreviation, Date release_date, Boolean is_released, Game game,
			List<Product> productExpansion, List<Sales> productSales, List<Offers> offers) {
		super();
		this.id = id;
		this.name = name;
		this.abbreviation = abbreviation;
		this.release_date = release_date;
		this.is_released = is_released;
		this.game = game;
		this.productExpansion = productExpansion;
		this.productSales = productSales;
		this.offers = offers;
	}

	public List<Offers> getOffers() {
		return offers;
	}

	public void setOffers(List<Offers> offers) {
		this.offers = offers;
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

	public List<Product> getProductExpansion() {
		return productExpansion;
	}

	public void setProductExpansion(List<Product> productExpansion) {
		this.productExpansion = productExpansion;
	}

}
