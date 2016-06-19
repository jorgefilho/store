package com.store.checkout.api.rule.promotion;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.store.checkout.api.StoreApiApplication;
import com.store.checkout.api.rule.promotion.PromotionManager;
import com.store.checkout.api.service.shoppingcart.builder.ShoppingCartItemBuilder;
import com.store.checkout.api.service.shoppingcart.domain.ShoppingCartItem;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = StoreApiApplication.class)
@WebAppConfiguration
public class PromotionManagerTests {


	@Autowired
	private PromotionManager promotionManager;

	@Test
	public void apply_whenParameterIsNull_shouldReturnEmptyList() {
		final List<ShoppingCartItem> items = promotionManager.apply(null);

		assertNotNull(items);
		assertTrue(items.isEmpty());
	}

	@Test
	public void apply_whenParameterIsEmpty_shouldReturnListWithEmptyItem() {
		final ShoppingCartItem emptyItem = new ShoppingCartItem();
		final List<ShoppingCartItem> items = promotionManager.apply(emptyItem);

		assertNotNull(items);
		assertFalse(items.isEmpty());
		assertEquals(emptyItem, items.iterator().next());
	}

	@Test
	public void apply_whenParameterIsValidAndPricingRuleIsBuyOneGetOne_ShouldReturnListWithTwoItems() {
		final ShoppingCartItem shoppingCartItem = getShoppingCartItemToRuleBuyOneGetOne();

		final List<ShoppingCartItem> items = promotionManager.apply(shoppingCartItem);
	}

	private ShoppingCartItem getShoppingCartItemToRuleBuyOneGetOne() {
		return new ShoppingCartItemBuilder().sku("mbp").quantity(1).unitPrice(BigDecimal.valueOf(1399.99))
				.totalPrice(BigDecimal.valueOf(1399.99).multiply(new BigDecimal(1))).build();
	}

}
