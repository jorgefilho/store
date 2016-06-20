package com.store.checkout.api.rule.promotion;

import static com.store.checkout.api.rule.promotion.constants.BeanName.PRICE_DISCOUNT;
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
public class PriceDiscountRuleTest {

	private static final BigDecimal VALID_DISCOUNT = new BigDecimal(50.00);

	private static final int EXPECTED_NUMBER_OF_ITEMS = 1;

	private static final BigDecimal VALID_PRICE = BigDecimal.valueOf(549.99);

	private static final int VALID_QUANTITY_TO_ACTIVE = 4;

	private static final int VALID_QUANTITY_TO_NOT_ENABLE = 3;

	private static final BigDecimal VALID_TOTAL_DISCOUNT = VALID_DISCOUNT
			.multiply(new BigDecimal(VALID_QUANTITY_TO_ACTIVE));


	private static final BigDecimal VALID_TOTAL_PRICE = VALID_PRICE.multiply(new BigDecimal(VALID_QUANTITY_TO_ACTIVE));

	private static final BigDecimal EXPECTED_TOTAL_PRICE_WITH_DISCOUNT = VALID_TOTAL_PRICE
			.subtract(VALID_TOTAL_DISCOUNT);

	private static final BigDecimal EXPECTED_TOTAL_PRICE_WITHOUT_DISCOUNT = VALID_PRICE
			.multiply(new BigDecimal(VALID_QUANTITY_TO_NOT_ENABLE));

	private static final String VALID_SKU = "ipd";

	private PriceDiscountRule ruleService = new PriceDiscountRule();

	@Test
	public void apply_whenAllParametersAreValidAndExactQuantityToActive_shouldReturnListOfItemValidWithPromotionApplied() {

		final Promotion promotion = getPriceDiscount();

		final ShoppingCartItem shoppingCartItem = getShoppingCartItemValid(VALID_QUANTITY_TO_ACTIVE);

		final List<ShoppingCartItem> items = ruleService.apply(promotion, shoppingCartItem);

		assertNotNull(items);
		assertFalse(items.isEmpty());
		assertEquals(EXPECTED_NUMBER_OF_ITEMS, items.size());

		BigDecimal totalPrice = BigDecimal.ZERO;

		for (final ShoppingCartItem item : items) {
			totalPrice = totalPrice.add(item.getTotalPrice());
		}

		assertEquals(EXPECTED_TOTAL_PRICE_WITH_DISCOUNT, totalPrice);

	}

	@Test
	public void apply_whenAllParametersAreValidAndQuantityNotEnable_shouldReturnListOfItemValidWithoutPromotionApplied() {

		final Promotion promotion = getPriceDiscount();

		final ShoppingCartItem shoppingCartItem = getShoppingCartItemValid(VALID_QUANTITY_TO_NOT_ENABLE);

		final List<ShoppingCartItem> items = ruleService.apply(promotion, shoppingCartItem);

		assertNotNull(items);
		assertFalse(items.isEmpty());
		assertEquals(EXPECTED_NUMBER_OF_ITEMS, items.size());

		BigDecimal totalPrice = BigDecimal.ZERO;

		for (final ShoppingCartItem item : items) {
			totalPrice = totalPrice.add(item.getTotalPrice());
		}

		assertEquals(EXPECTED_TOTAL_PRICE_WITHOUT_DISCOUNT, totalPrice);

	}

	private ShoppingCartItem getShoppingCartItemValid(final int quantity) {
		return new ShoppingCartItemBuilder().sku(VALID_SKU).quantity(quantity).unitPrice(VALID_PRICE)
				.totalPrice(VALID_PRICE.multiply(new BigDecimal(quantity))).build();
	}

	private Promotion getPriceDiscount() {
		return new PromotionBuilder().sku(VALID_SKU).type(PRICE_DISCOUNT).numberOfItems(VALID_QUANTITY_TO_ACTIVE)
				.pricingRule(new PricingRule(VALID_DISCOUNT))
				.build();
	}

}
