package com.store.checkout.api.rule.promotion.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.store.checkout.api.rule.promotion.PriceDiscountRule;
import com.store.checkout.api.rule.promotion.constants.BeanName;

@Component
public class PriceDiscountRuleBean implements RuleBean {

	@Autowired
	private PriceDiscountRule priceDiscountRule;

	@Bean(name = BeanName.PRICE_DISCOUNT)
	public PriceDiscountRule processRule() {
		return this.priceDiscountRule;
	}
}