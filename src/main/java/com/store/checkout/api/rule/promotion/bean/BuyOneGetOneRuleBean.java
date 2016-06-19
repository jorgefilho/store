package com.store.checkout.api.rule.promotion.bean;

import static com.store.checkout.api.rule.promotion.constants.BeanName.BY_ONE_GET_ONE_BEAN;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.store.checkout.api.rule.promotion.BuyOneGetOneRule;

@Component
public class BuyOneGetOneRuleBean implements RuleBean {

	@Autowired
	private BuyOneGetOneRule buyOneGetOneRuleService;

	@Bean(name = BY_ONE_GET_ONE_BEAN)
	public BuyOneGetOneRule processEvent() {
		return this.buyOneGetOneRuleService;
	}
}