package com.store.checkout.api.service.shoppingcart.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ShoppingCartItem implements Serializable {

	private static final long serialVersionUID = 1L;

	private String sku;

	private int quantity;

	private BigDecimal unitPrice;

	private BigDecimal totalPrice = BigDecimal.ZERO;

	@JsonIgnore
	private boolean gift = false;

	@JsonIgnore
	private int quantityIsGift;

	@JsonIgnore
	private String skuLink;

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

	public String getSkuLink() {
		return skuLink;
	}

	public void setSkuLink(String skuLink) {
		this.skuLink = skuLink;
	}

	public boolean isGift() {
		return gift;
	}

	public void setGift(boolean gift) {
		this.gift = gift;
	}

	public int getQuantityIsGift() {
		return quantityIsGift;
	}

	public void setQuantityIsGift(int quantityIsGift) {
		this.quantityIsGift = quantityIsGift;
	}

	@Override
	public String toString() {
		return "ShoppingCartItem [sku=" + sku + ", quantity=" + quantity + ", unitPrice=" + unitPrice + ", totalPrice="
				+ totalPrice + ", gift=" + gift + ", quantityIsGift=" + quantityIsGift + ", skuLink=" + skuLink + "]";
	}
}
