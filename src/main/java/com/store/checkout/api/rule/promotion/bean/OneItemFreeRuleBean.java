package com.store.checkout.api.rule.promotion.bean;

import static com.store.checkout.api.rule.promotion.constants.BeanName.ONE_ITEM_FREE_BEAN;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.store.checkout.api.rule.promotion.OneItemFreeRule;

@Component
public class OneItemFreeRuleBean implements RuleBean {

	@Autowired
	private OneItemFreeRule oneItemFreeRuleService;

	@Bean(name = ONE_ITEM_FREE_BEAN)
	public OneItemFreeRule processEvent() {
		return this.oneItemFreeRuleService;
	}
}