package com.store.checkout.api.rule.promotion.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.store.checkout.api.rule.promotion.BuyOneGetOneRule;
import com.store.checkout.api.rule.promotion.constants.BeanName;

@Component
public class BuyOneGetOneRuleBean implements RuleBean {

	public static final Logger LOGGER = LoggerFactory.getLogger(BuyOneGetOneRuleBean.class);

	@Autowired
	private BuyOneGetOneRule buyOneGetOneRule;

	@Bean(name = BeanName.BY_ONE_GET_ONE)
	public BuyOneGetOneRule processRule() {
		if (this.buyOneGetOneRule == null) {
			LOGGER.error("Could't loading correctly!!!");
		}
		return this.buyOneGetOneRule;
	}
}