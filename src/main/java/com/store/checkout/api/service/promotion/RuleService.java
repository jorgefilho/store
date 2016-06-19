package com.store.checkout.api.service.promotion;

import java.util.List;

import com.store.checkout.api.repository.domain.Promotion;
import com.store.checkout.api.session.domain.ShoppingCartItem;

public interface RuleService {
	List<ShoppingCartItem> apply(Promotion promotion, ShoppingCartItem shoppingCartItem);
}