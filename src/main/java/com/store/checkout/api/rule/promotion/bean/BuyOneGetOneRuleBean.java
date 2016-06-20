package com.store.checkout.api.rule.promotion.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.store.checkout.api.rule.promotion.BuyOneGetOneRule;
import com.store.checkout.api.rule.promotion.constants.BeanName;

@Component
public class BuyOneGetOneRuleBean implements RuleBean {

	@Autowired
	private BuyOneGetOneRule buyOneGetOneRule;

	@Bean(name = BeanName.BY_ONE_GET_ONE)
	public BuyOneGetOneRule processRule() {
		return this.buyOneGetOneRule;
	}
}