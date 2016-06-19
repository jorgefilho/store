package com.store.checkout.api.rule.promotion;

import static com.store.checkout.api.repository.domain.enums.PromotionType.ONE_ITEM_FREE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.store.checkout.api.repository.domain.Promotion;
import com.store.checkout.api.rule.promotion.OneItemFreeRule;
import com.store.checkout.api.rule.promotion.builder.PromotionBuilder;
import com.store.checkout.api.service.shoppingcart.builder.ShoppingCartItemBuilder;
import com.store.checkout.api.service.shoppingcart.domain.ShoppingCartItem;

@RunWith(JUnit4.class)
public class OneItemFreeRuleTest {

	private static final int EXPECTED_NUMBER_OF_ITEMS = 1;

	private static final BigDecimal VALID_PRICE = BigDecimal.valueOf(109.50);

	private static final int VALID_QUANTITY_TO_ACTIVE_ONCE = 3;
	
	private static final int VALID_QUANTITY_TO_ACTIVE_TWICE = VALID_QUANTITY_TO_ACTIVE_ONCE * 2;
	
	private static final int VALID_QUANTITY_TO_ACTIVE_ONCE_BUT_NOT_ENABLE_TWICE = VALID_QUANTITY_TO_ACTIVE_ONCE + 1;

	private static final BigDecimal EXPECTED_TOTAL_PRICE_WITH_ONCE = VALID_PRICE.multiply(new BigDecimal(VALID_QUANTITY_TO_ACTIVE_ONCE - 1));

	private static final BigDecimal EXPECTED_TOTAL_PRICE_WITH_TWO = VALID_PRICE.multiply(new BigDecimal(VALID_QUANTITY_TO_ACTIVE_TWICE - 2));

	private static final BigDecimal EXPECTED_TOTAL_PRICE_WITH_ONCE_BUT_NOT_TWO = EXPECTED_TOTAL_PRICE_WITH_ONCE.add(VALID_PRICE);

	private static final String VALID_SKU = "atv";

	private OneItemFreeRule rule = new OneItemFreeRule();


	@Test
	public void apply_whenAllParametersAreValidAndExactQuantityToActiveOnce_shouldReturnListOfItemValidWithOnePromotionApplied() {

		final Promotion promotion = getOneItemFree();

		final ShoppingCartItem shoppingCartItem = getShoppingCartItemValid(VALID_QUANTITY_TO_ACTIVE_ONCE);

		final List<ShoppingCartItem> items = rule.apply(promotion, shoppingCartItem);

		assertNotNull(items);
		assertFalse(items.isEmpty());
		assertEquals(EXPECTED_NUMBER_OF_ITEMS, items.size());

		BigDecimal totalPrice = BigDecimal.ZERO;

		for (final ShoppingCartItem item : items) {
			totalPrice = totalPrice.add(item.getTotalPrice());
		}

		assertEquals(EXPECTED_TOTAL_PRICE_WITH_ONCE, totalPrice);

	}

	@Test
	public void apply_whenAllParametersAreValidAndExactQuantityToActiveTwice_shouldReturnListOfItemValidWithTwoPromotionApplied() {

		final Promotion promotion = getOneItemFree();

		final ShoppingCartItem shoppingCartItem = getShoppingCartItemValid(VALID_QUANTITY_TO_ACTIVE_TWICE);

		final List<ShoppingCartItem> items = rule.apply(promotion, shoppingCartItem);

		assertNotNull(items);
		assertFalse(items.isEmpty());
		assertEquals(EXPECTED_NUMBER_OF_ITEMS, items.size());

		BigDecimal totalPrice = BigDecimal.ZERO;

		for (final ShoppingCartItem item : items) {
			totalPrice = totalPrice.add(item.getTotalPrice());
		}

		assertEquals(EXPECTED_TOTAL_PRICE_WITH_TWO, totalPrice);

	}

	@Test
	public void apply_whenAllParametersAreValidAndQuantityIsGreaterToActivateOnceAndLessToActiveTwice_shouldReturnListOfItemValidWithOnePromotionApplied() {

		final Promotion promotion = getOneItemFree();

		final ShoppingCartItem shoppingCartItem = getShoppingCartItemValid(
				VALID_QUANTITY_TO_ACTIVE_ONCE_BUT_NOT_ENABLE_TWICE);

		final List<ShoppingCartItem> items = rule.apply(promotion, shoppingCartItem);

		assertNotNull(items);
		assertFalse(items.isEmpty());
		assertEquals(EXPECTED_NUMBER_OF_ITEMS, items.size());

		BigDecimal totalPrice = BigDecimal.ZERO;

		for (final ShoppingCartItem item : items) {
			totalPrice = totalPrice.add(item.getTotalPrice());
		}

		assertEquals(EXPECTED_TOTAL_PRICE_WITH_ONCE_BUT_NOT_TWO, totalPrice);

	}

	private ShoppingCartItem getShoppingCartItemValid(final int quantity) {
		return new ShoppingCartItemBuilder().sku(VALID_SKU).quantity(quantity)
				.unitPrice(VALID_PRICE).totalPrice(VALID_PRICE.multiply(new BigDecimal(quantity))).build();
	}

	private Promotion getOneItemFree() {
		return new PromotionBuilder().sku(VALID_SKU).type(ONE_ITEM_FREE).numberOfItems(VALID_QUANTITY_TO_ACTIVE_ONCE)
				.build();
	}
}
