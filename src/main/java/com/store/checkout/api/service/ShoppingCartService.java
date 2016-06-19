package com.store.checkout.api.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.checkout.api.repository.domain.Product;
import com.store.checkout.api.session.domain.ShoppingCart;
import com.store.checkout.api.session.domain.ShoppingCartItem;

@Service
public class ShoppingCartService {

	@Autowired
	private ShoppingCart shoppingCart;

	@Autowired
	private PromotionManager promotionManager;

	public ShoppingCart addItem(final Product product, final int quantity) {

		ShoppingCartItem item = shoppingCart.getItem(product.getSku());

		if (item == null) {
			item = new ShoppingCartItem();
			item.setSku(product.getSku());
			item.setUnitPrice(product.getPrice());
		}
		item.setTotalPrice(product.getPrice().multiply(new BigDecimal(quantity)));
		item.setQuantity(quantity);

		final List<ShoppingCartItem> items = promotionManager.apply(item);

		items.forEach(i -> {
			shoppingCart.addItem(i);
		});

		return shoppingCart;
	}

	public ShoppingCart removeItem(final String sku) {
		final ShoppingCartItem item = shoppingCart.getItem(sku);
		return shoppingCart.removeItem(item);
	}
}
