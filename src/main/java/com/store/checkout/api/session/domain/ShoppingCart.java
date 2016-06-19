package com.store.checkout.api.session.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS, value = "session")
public class ShoppingCart implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<ShoppingCartItem> items = new ArrayList<>();

	private BigDecimal total = BigDecimal.ZERO;

	public void addItem(final ShoppingCartItem item) {
		items.add(item);
		total.add(item.getTotalPrice());
	}

	public void removeItem(final ShoppingCartItem item) {
		items.remove(item);
		total.subtract(item.getTotalPrice());
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void removeAllItems() {
		items.clear();
		total = BigDecimal.ZERO;
	}

	public ShoppingCartItem getItem(final String sku) {
		ShoppingCartItem shoppingCartItem = null;

		Optional<ShoppingCartItem> findFirst = items.stream().filter(item -> item.getSku().equals(sku)).findFirst();
		if (findFirst.isPresent()) {
			shoppingCartItem = findFirst.get();
		}
		return shoppingCartItem;
	}

	public List<ShoppingCartItem> getItems() {
		return items;
	}
}