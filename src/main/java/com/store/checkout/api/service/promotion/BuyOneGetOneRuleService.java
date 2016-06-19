package com.store.checkout.api.service.promotion;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.store.checkout.api.repository.domain.Promotion;
import com.store.checkout.api.session.domain.ShoppingCartItem;

@Service
public class BuyOneGetOneRuleService implements RuleService {

	@Override
	public List<ShoppingCartItem> apply(final Promotion promotion, final ShoppingCartItem shoppingCartItem) {

		final List<ShoppingCartItem> items = new ArrayList<>();
		if (promotion != null && shoppingCartItem != null) {
			items.add(shoppingCartItem);

			// Active the promotion
			if ((shoppingCartItem.getQuantity() >= promotion.getNumberOfItems())) {
				final ShoppingCartItem itemPromotion = new ShoppingCartItem();
				itemPromotion.setSku(promotion.getPricingRule().getSku());
				itemPromotion.setQuantity(shoppingCartItem.getQuantity());
				itemPromotion.setUnitPrice(BigDecimal.ZERO);
				itemPromotion.setTotalPrice(BigDecimal.ZERO);
				items.add(itemPromotion);
			}
		}
		return items;
	}
}