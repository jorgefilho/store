package com.store.checkout.api.builder;

import com.store.checkout.api.controller.contract.CheckoutItem;

public class CheckoutItemBuilder {

	private CheckoutItem checkoutItem = new CheckoutItem();

	public CheckoutItemBuilder sku(final String sku) {
		this.checkoutItem.setSku(sku);
		return this;
	}

	public CheckoutItemBuilder quantity(final int quantity) {
		this.checkoutItem.setQuantity(quantity);
		return this;
	}

	public CheckoutItem build() {
		return this.checkoutItem;
	}
}
