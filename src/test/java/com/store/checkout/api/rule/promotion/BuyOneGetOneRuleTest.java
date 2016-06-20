package com.store.checkout.api.rule.promotion;

import static com.store.checkout.api.rule.promotion.constants.BeanName.BY_ONE_GET_ONE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.store.checkout.api.builder.PromotionBuilder;
import com.store.checkout.api.builder.ShoppingCartItemBuilder;
import com.store.checkout.api.repository.domain.PricingRule;
import com.store.checkout.api.repository.domain.Promotion;
import com.store.checkout.api.service.shoppingcart.domain.ShoppingCartItem;

@RunWith(JUnit4.class)
public class BuyOneGetOneRuleTest {

	private static final String VALID_SKU = "mbp";

	private static final int EXPECTED_NUMBER_OF_ITEMS = 2;

	private static final int VALID_QUANTITY_ONE = 1;

	private static final int VALID_QUANTITY_TWO = 2;

	private static final BigDecimal VALID_PRICE = BigDecimal.valueOf(1399.99);

	private static final BigDecimal EXPECTED_TOTAL_PRICE_FOR_QUANTITY_ONE = VALID_PRICE;

	private static final BigDecimal EXPECTED_TOTAL_PRICE_FOR_QUANTITY_TWO = VALID_PRICE
			.multiply(new BigDecimal(VALID_QUANTITY_TWO));


	private BuyOneGetOneRule rule = new BuyOneGetOneRule();

	@Test
	public void apply_whenAllParameterAreValidAndQuantityIsOne_shoulReturnListOfItemsValidWithTwoItems() {

		final Promotion promotion = getBuyOneGetOneValid();

		final ShoppingCartItem shoppingCartItem = getShoppingCartItemValid(VALID_QUANTITY_ONE);

		final List<ShoppingCartItem> items = rule.apply(promotion, shoppingCartItem);

		assertNotNull(items);
		assertFalse(items.isEmpty());
		assertEquals(EXPECTED_NUMBER_OF_ITEMS, items.size());

		BigDecimal totalPrice = BigDecimal.ZERO;

		for (final ShoppingCartItem item : items) {
			totalPrice = totalPrice.add(item.getTotalPrice());
		}

		assertEquals(EXPECTED_TOTAL_PRICE_FOR_QUANTITY_ONE, totalPrice);

	}

	@Test
	public void apply_whenAllParameterAreValidAndQuantityIsTwo_shoulReturnListOfItemsValidWithTwoItems() {

		final Promotion promotion = getBuyOneGetOneValid();

		final ShoppingCartItem shoppingCartItem = getShoppingCartItemValid(VALID_QUANTITY_TWO);

		final List<ShoppingCartItem> items = rule.apply(promotion, shoppingCartItem);

		assertNotNull(items);
		assertFalse(items.isEmpty());
		assertEquals(EXPECTED_NUMBER_OF_ITEMS, items.size());

		BigDecimal totalPrice = BigDecimal.ZERO;

		for (final ShoppingCartItem item : items) {
			totalPrice = totalPrice.add(item.getTotalPrice());
		}

		assertEquals(EXPECTED_TOTAL_PRICE_FOR_QUANTITY_TWO, totalPrice);

	}

	private ShoppingCartItem getShoppingCartItemValid(final int quantity) {
		return new ShoppingCartItemBuilder().sku(VALID_SKU).quantity(quantity)
				.unitPrice(VALID_PRICE).totalPrice(VALID_PRICE.multiply(new BigDecimal(quantity))).build();
	}

	private Promotion getBuyOneGetOneValid() {
		return new PromotionBuilder().sku(VALID_SKU).type(BY_ONE_GET_ONE).numberOfItems(VALID_QUANTITY_ONE)
				.pricingRule(new PricingRule("vga")).build();
	}
}
