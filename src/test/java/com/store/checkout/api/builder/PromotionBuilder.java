package com.store.checkout.api.builder;

import com.store.checkout.api.repository.domain.PricingRule;
import com.store.checkout.api.repository.domain.Promotion;

public class PromotionBuilder {

	private String sku;

	private String type;
	
	private int numberOfItems;
	
	private PricingRule pricingRule;

	public PromotionBuilder sku (final String sku){
		this.sku = sku;
		return this;
	}

	public PromotionBuilder type(final String type) {
		this.type = type;
		return this;
	}
	
	public PromotionBuilder numberOfItems(final int numberOfItems) {
		this.numberOfItems = numberOfItems;
		return this;
	}

	public PromotionBuilder pricingRule(final PricingRule princingRule) {
		this.pricingRule = princingRule;
		return this;
	}
	
	public Promotion build() {
		return new Promotion(this.sku, this.type, this.numberOfItems, this.pricingRule);
	}
}
