package com.store.checkout.api.rule.promotion.bean;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.store.checkout.api.rule.promotion.OneItemFreeRule;
import com.store.checkout.api.rule.promotion.constants.BeanName;

@Component
public class OneItemFreeRuleBean implements RuleBean {

	public static final Logger LOGGER = LoggerFactory.getLogger(OneItemFreeRuleBean.class);

	@Autowired
	private OneItemFreeRule oneItemFreeRuleService;

	@Bean(name = BeanName.ONE_ITEM_FREE)
	public OneItemFreeRule processEvent() {
		if (this.oneItemFreeRuleService == null) {
			LOGGER.error("Could't loading correctly!!!");
		}
		return this.oneItemFreeRuleService;
	}
}