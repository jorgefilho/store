package com.store.checkout.api.rule.promotion.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.store.checkout.api.rule.promotion.PriceDiscountRule;
import com.store.checkout.api.rule.promotion.constants.BeanName;

@Component
public class PriceDiscountRuleBean implements RuleBean {

	public static final Logger LOGGER = LoggerFactory.getLogger(PriceDiscountRuleBean.class);

	@Autowired
	private PriceDiscountRule priceDiscountRule;

	@Bean(name = BeanName.PRICE_DISCOUNT)
	public PriceDiscountRule processRule() {
		if (this.priceDiscountRule == null) {
			LOGGER.error("Could't loading correctly!!!");
		}
		return this.priceDiscountRule;
	}
}