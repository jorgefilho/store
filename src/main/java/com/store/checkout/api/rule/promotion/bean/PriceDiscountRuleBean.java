package com.store.checkout.api.rule.promotion.bean;

import static com.store.checkout.api.rule.promotion.constants.BeanName.PRICE_DISCOUNT_BEAN;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.store.checkout.api.rule.promotion.PriceDiscountRule;

@Component
public class PriceDiscountRuleBean implements RuleBean {

	@Autowired
	private PriceDiscountRule priceDiscountRuleService;

	@Bean(name = PRICE_DISCOUNT_BEAN)
	public PriceDiscountRule processEvent() {
		return this.priceDiscountRuleService;
	}
}