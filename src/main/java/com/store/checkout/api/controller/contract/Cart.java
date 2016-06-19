package com.store.checkout.api.controller.contract;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.store.checkout.api.service.shoppingcart.domain.ShoppingCartItem;

public class Cart implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<ShoppingCartItem> items = new ArrayList<>();

	private BigDecimal total = BigDecimal.ZERO;

	public Cart(final List<ShoppingCartItem> items, final BigDecimal total) {
		this.items = items;
		this.total = total;
	}

	public List<ShoppingCartItem> getItems() {
		return items;
	}

	public BigDecimal getTotal() {
		return total;
	}

	@Override
	public String toString() {
		return "Cart [items=" + items + ", total=" + total + "]";
	}
}
