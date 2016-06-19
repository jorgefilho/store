package com.store.checkout.api.service.promotion;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.store.checkout.api.repository.domain.Promotion;
import com.store.checkout.api.session.domain.ShoppingCartItem;

@Service
public class OneItemFreeRuleService implements RuleService {

	@Override
	public List<ShoppingCartItem> apply(final Promotion promotion, final ShoppingCartItem shoppingCartItem) {
		final List<ShoppingCartItem> items = new ArrayList<>();

		if (promotion != null && shoppingCartItem != null) {
			// Active the promotion
			if ((shoppingCartItem.getQuantity() >= promotion.getNumberOfItems())) {

				final int times = shoppingCartItem.getQuantity() / promotion.getNumberOfItems();

				shoppingCartItem.setQuantity(shoppingCartItem.getQuantity() - times);
				shoppingCartItem.setTotalPrice(shoppingCartItem.getTotalPrice()
						.subtract(shoppingCartItem.getUnitPrice().multiply(new BigDecimal(times))));
			}
			items.add(shoppingCartItem);
		}
		return items;
	}
}
