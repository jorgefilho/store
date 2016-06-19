package com.store.checkout.api.service.shoppingcart;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.checkout.api.repository.domain.Product;
import com.store.checkout.api.rule.promotion.PromotionManager;
import com.store.checkout.api.service.shoppingcart.domain.ShoppingCart;
import com.store.checkout.api.service.shoppingcart.domain.ShoppingCartItem;

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
			item.setQuantity(quantity);
			item.setTotalPrice(product.getPrice().multiply(new BigDecimal(quantity)));
		} else {
			shoppingCart.withdrawItem(item);
			
			int newQuantity = item.getQuantity() + quantity;
			
			item.setQuantity(newQuantity);
			item.setTotalPrice(product.getPrice().multiply(new BigDecimal(newQuantity)));
			
			if (item.isGift()) {
				final BigDecimal totalDiscount = getGiftDiscount(item.getQuantityIsGift(), product.getPrice());
				item.setTotalPrice(item.getTotalPrice().subtract(totalDiscount));
			}
		}

		final List<ShoppingCartItem> items = promotionManager.apply(item);

		items.forEach(i -> {
			shoppingCart.addItem(i);
		});

		return shoppingCart;
	}

	private BigDecimal getGiftDiscount(final int quantityOfGift, final BigDecimal price) {
		final BigDecimal discount = price.multiply(new BigDecimal(quantityOfGift));
		return discount;
	}

	public ShoppingCart removeItem(final String sku) {
		final ShoppingCartItem item = shoppingCart.getItem(sku);
		return shoppingCart.removeItem(item);
	}

	public ShoppingCart removeAllItems() {
		return shoppingCart.removeAllItems();
	}

	public ShoppingCart getShoppinCart() {
		return shoppingCart;
	}
}
