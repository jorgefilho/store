package com.store.checkout.api.builder;

import java.math.BigDecimal;

import com.store.checkout.api.service.shoppingcart.domain.ShoppingCartItem;

public class ShoppingCartItemBuilder {

	private ShoppingCartItem shoppingCartItem = new ShoppingCartItem();

	public ShoppingCartItemBuilder sku(final String sku) {
		this.shoppingCartItem.setSku(sku);
		return this;
	}

	public ShoppingCartItemBuilder quantity(final int quantity) {
		this.shoppingCartItem.setQuantity(quantity);
		return this;
	}

	public ShoppingCartItemBuilder unitPrice(final BigDecimal unitPrice) {
		this.shoppingCartItem.setUnitPrice(unitPrice);
		return this;
	}

	public ShoppingCartItemBuilder totalPrice(final BigDecimal totalPrice) {
		this.shoppingCartItem.setTotalPrice(totalPrice);
		return this;
	}

	public ShoppingCartItem build() {
		return this.shoppingCartItem;
	}
}