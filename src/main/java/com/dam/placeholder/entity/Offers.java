package com.dam.placeholder.entity;

import com.dam.placeholder.request.OffersRequest;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Offers {

	@Id
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "PRODUCT_ID")
	private Product product;

	@ManyToOne
	@JoinColumn(name = "EXPANSION_ID")
	private Expansion expansion;

	private Integer quantity;

	private Double unitaryPrice;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Expansion getExpansion() {
		return expansion;
	}

	public void setExpansion(Expansion expansion) {
		this.expansion = expansion;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getUnitaryPrice() {
		return unitaryPrice;
	}

	public void setUnitaryPrice(Double unitaryPrice) {
		this.unitaryPrice = unitaryPrice;
	}

	public Offers() {
		super();
	}

	public Offers(Integer id, Product product, Expansion expansion, Integer quantity, Double unitaryPrice) {
		super();
		this.id = id;
		this.product = product;
		this.expansion = expansion;
		this.quantity = quantity;
		this.unitaryPrice = unitaryPrice;
	}

	public Offers(OffersRequest request) {

		this.expansion = request.getExpansion();
		this.product = request.getProduct();
		this.id = request.getId();
		this.quantity = request.getQuantity();
		this.unitaryPrice = request.getUnitaryPrice();

	}

	/**
	 * Incrementa la cantidad ofrecida en la cantidad que recibe. En caso de que aún
	 * no haya ninguna cantidad, la inicializa a 0
	 * 
	 * @param quantity quantity to add
	 * @return
	 */
	public void increaseQuantity(Integer quantity) {
		if (this.quantity == null) {
			this.quantity = 0;
		}
		this.quantity += quantity;
	}

	/**
	 * Reduce la cantidad ofrecida en la cantidad que recibe. En caso de que fuera a
	 * reducir la cantidad por debajo de 0 (o la cantidad original fuera 0), la deja
	 * en 0 y devuelve false para indicar que no se ha podido reducir toda la
	 * cantidad pasada. En cualquier otro caso, lo reduce y devuelve true
	 * 
	 * @param quantity quantity to decrease
	 * @return
	 */
	public boolean decreaseQuantity(Integer quantity) {
		if (this.quantity == 0 || (this.quantity - quantity) < 0) {
			this.quantity = 0;
			return false;
		}

		this.quantity -= quantity;
		return true;

	}

}
