package com.store.checkout.api.service.shoppingcart.domain;

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

		this.items.add(item);
		this.total = total.add(item.getTotalPrice());
	}

	public ShoppingCart removeItem(final ShoppingCartItem item) {
		this.items.remove(item);
		this.total = total.subtract(item.getTotalPrice());

		if (item.getSkuLink() != null) {
			final ShoppingCartItem itemLinked = this.getItem(item.getSkuLink());
			this.items.remove(itemLinked);
			this.total = total.subtract(itemLinked.getTotalPrice());
		}

		return this;
	}

	public void withdrawItem(final ShoppingCartItem item) {
		this.items.remove(item);
		this.total = total.subtract(item.getTotalPrice());
	}

	public BigDecimal getTotal() {
		return this.total;
	}

	public ShoppingCart removeAllItems() {
		this.items.clear();
		this.total = BigDecimal.ZERO;
		return this;
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
		return this.items;
	}

}