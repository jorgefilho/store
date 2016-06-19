package com.store.checkout.api.service.promotion.bean;

import static com.store.checkout.api.service.promotion.constants.BeanName.PRICE_DISCOUNT_BEAN;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.store.checkout.api.service.promotion.PriceDiscountRuleService;

@Component
public class PriceDiscountRuleBean implements RuleBean {

	@Autowired
	private PriceDiscountRuleService priceDiscountRuleService;

	@Bean(name = PRICE_DISCOUNT_BEAN)
	public PriceDiscountRuleService processEvent() {
		return this.priceDiscountRuleService;
	}
}