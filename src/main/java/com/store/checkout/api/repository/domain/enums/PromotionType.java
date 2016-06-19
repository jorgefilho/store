package com.store.checkout.api.repository.domain.enums;

import static com.store.checkout.api.service.promotion.constants.BeanName.BY_ONE_GET_ONE_BEAN;
import static com.store.checkout.api.service.promotion.constants.BeanName.ONE_ITEM_FREE_BEAN;
import static com.store.checkout.api.service.promotion.constants.BeanName.PRICE_DISCOUNT_BEAN;

public enum PromotionType {
	BY_ONE_GET_ONE(BY_ONE_GET_ONE_BEAN), 
	ONE_ITEM_FREE(ONE_ITEM_FREE_BEAN), 
	PRICE_DISCOUNT(PRICE_DISCOUNT_BEAN);

	private String beanName;

	private PromotionType(final String beanName) {
		this.beanName = beanName;
	}

	public String getBeanName() {
		return beanName;
	}
}
