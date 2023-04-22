package com.dam.placeholder.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.dam.placeholder.request.ProductRequest;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "PRODUCT")
public class Product {

	@Id
	private Integer id;
	@Column(name = "NAME")
	private String name;
	@Column(name = "RARITY")
	private String rarity;
	@Column(name = "QUANTITY")
	private Integer quantity;
	@Column(name = "IMAGE")
	private String image;

	@ManyToMany
	@JoinTable(name = "PRODUCT_EXPANSION", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "expansion_id"))
	Set<Expansion> expansion;

	@OneToMany(mappedBy = "product", targetEntity = SaleDetails.class, cascade = CascadeType.REMOVE, orphanRemoval = true)
	List<Sales> productSales;

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

	public String getRarity() {
		return rarity;
	}

	public void setRarity(String rarity) {
		this.rarity = rarity;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public List<Sales> getProductSales() {
		return productSales;
	}

	public void setProductSales(List<Sales> productSales) {
		this.productSales = productSales;
	}

	public Set<Expansion> getExpansion() {
		return expansion;
	}

	public void setExpansion(Set<Expansion> expansion) {
		this.expansion = expansion;
	}

	public Product(ProductRequest prod) {
		this.id = prod.getId();
		this.name = prod.getName();
		this.rarity = prod.getRarity();
		this.image = prod.getImage();
		this.quantity = prod.getQuantity();

		Set<Expansion> producExpansionList = new HashSet();
		producExpansionList.addAll(prod.getExpansions());
		this.expansion = producExpansionList;
	}

	public Product(Integer id, String name, String rarity, Integer quantity, String image, Set<Expansion> expansion,
			List<Sales> productSales) {
		super();
		this.id = id;
		this.name = name;
		this.rarity = rarity;
		this.quantity = quantity;
		this.image = image;
		this.expansion = expansion;
		this.productSales = productSales;
	}

	public Product() {
		super();
	}

}
