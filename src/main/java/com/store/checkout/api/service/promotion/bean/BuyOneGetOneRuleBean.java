package com.store.checkout.api.service.promotion.bean;

import static com.store.checkout.api.service.promotion.constants.BeanName.BY_ONE_GET_ONE_BEAN;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.store.checkout.api.service.promotion.BuyOneGetOneRuleService;

@Component
public class BuyOneGetOneRuleBean implements RuleBean {

	@Autowired
	private BuyOneGetOneRuleService buyOneGetOneRuleService;

	@Bean(name = BY_ONE_GET_ONE_BEAN)
	public BuyOneGetOneRuleService processEvent() {
		return this.buyOneGetOneRuleService;
	}
}