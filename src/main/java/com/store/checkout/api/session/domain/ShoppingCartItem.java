package com.store.checkout.api.session.domain;

import java.io.Serializable;
import java.math.BigDecimal;

public class ShoppingCartItem implements Serializable {

	private static final long serialVersionUID = 1L;

	private String sku;

	private String name;

	private int quantity;

	private BigDecimal originPrice;

	private BigDecimal finalPrice;

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getOriginPrice() {
		return originPrice;
	}

	public void setOriginPrice(BigDecimal originPrice) {
		this.originPrice = originPrice;
	}

	public BigDecimal getFinalPrice() {
		return finalPrice;
	}

	public void setFinalPrice(BigDecimal finalPrice) {
		this.finalPrice = finalPrice;
	}

	@Override
	public String toString() {
		return "ShoppingCartItem [sku=" + sku + ", name=" + name + ", quantity=" + quantity + ", originPrice="
				+ originPrice + ", finalPrice=" + finalPrice + "]";
	}
}
