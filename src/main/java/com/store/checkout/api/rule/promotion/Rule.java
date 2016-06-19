package com.store.checkout.api.rule.promotion;

import java.util.List;

import com.store.checkout.api.repository.domain.Promotion;
import com.store.checkout.api.service.shoppingcart.domain.ShoppingCartItem;

public interface Rule {
	List<ShoppingCartItem> apply(Promotion promotion, ShoppingCartItem shoppingCartItem);
}