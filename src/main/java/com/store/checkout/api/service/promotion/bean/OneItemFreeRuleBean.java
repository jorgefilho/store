package com.store.checkout.api.service.promotion.bean;

import static com.store.checkout.api.service.promotion.constants.BeanName.ONE_ITEM_FREE_BEAN;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.store.checkout.api.service.promotion.OneItemFreeRuleService;

@Component
public class OneItemFreeRuleBean implements RuleBean {

	@Autowired
	private OneItemFreeRuleService oneItemFreeRuleService;

	@Bean(name = ONE_ITEM_FREE_BEAN)
	public OneItemFreeRuleService processEvent() {
		return this.oneItemFreeRuleService;
	}
}