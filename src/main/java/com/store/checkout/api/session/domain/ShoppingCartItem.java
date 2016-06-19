package com.store.checkout.api.session.domain;

import java.io.Serializable;
import java.math.BigDecimal;

public class ShoppingCartItem implements Serializable {

	private static final long serialVersionUID = 1L;

	private String sku;

	private int quantity;

	private BigDecimal unitPrice;

	private BigDecimal totalPrice = BigDecimal.ZERO;

	public void setSku(String sku) {
		this.sku = sku;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getSku() {
		return sku;
	}

	public int getQuantity() {
		return quantity;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	@Override
	public String toString() {
		return "ShoppingCartItem [sku=" + sku + ", quantity=" + quantity + ", unitPrice=" + unitPrice + ", totalPrice="
				+ totalPrice + "]";
	}
}
