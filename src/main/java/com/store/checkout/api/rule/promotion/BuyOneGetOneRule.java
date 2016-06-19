package com.store.checkout.api.rule.promotion;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.store.checkout.api.repository.domain.Promotion;
import com.store.checkout.api.service.shoppingcart.domain.ShoppingCartItem;

@Service
public class BuyOneGetOneRule implements Rule {

	@Override
	public List<ShoppingCartItem> apply(final Promotion promotion, final ShoppingCartItem shoppingCartItem) {

		final List<ShoppingCartItem> items = new ArrayList<>();
		if (promotion != null && shoppingCartItem != null) {

			// Active the promotion
			if ((shoppingCartItem.getQuantity() >= promotion.getNumberOfItems())) {
				final ShoppingCartItem itemPromotion = new ShoppingCartItem();
				itemPromotion.setSku(promotion.getPricingRule().getSku());
				itemPromotion.setQuantity(shoppingCartItem.getQuantity());
				itemPromotion.setUnitPrice(BigDecimal.ZERO);
				itemPromotion.setTotalPrice(BigDecimal.ZERO);
				itemPromotion.setSkuLink(shoppingCartItem.getSku());
				itemPromotion.setGift(true);
				itemPromotion.setQuantityIsGift(itemPromotion.getQuantityIsGift() + 1);

				shoppingCartItem.setSkuLink(promotion.getPricingRule().getSku());

				items.add(itemPromotion);
			}
			items.add(shoppingCartItem);
		}
		return items;
	}
}
