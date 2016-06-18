package com.store.checkout.api.session.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCart implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<ShoppingCartItem> items = new ArrayList<>();

	private BigDecimal total;

	public void addItem(final ShoppingCartItem item) {
		items.add(item);
		total.add(item.getFinalPrice());
	}

	public void removeItem(final ShoppingCartItem item) {
		items.remove(item);
		total.subtract(item.getFinalPrice());
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void removeAllItems() {
		items.clear();
		total = BigDecimal.ZERO;
	}
}
