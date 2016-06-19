package com.store.checkout.api.repository.domain;

import java.io.Serializable;
import java.math.BigDecimal;

public class PricingRule implements Serializable {

	private static final long serialVersionUID = 1L;

	private String sku;

	private BigDecimal discount;

	public PricingRule(String sku) {
		this.sku = sku;
	}

	public PricingRule(BigDecimal discount) {
		this.discount = discount;
	}

	public String getSku() {
		return sku;
	}

	public BigDecimal getDiscount() {
		return this.discount;
	}

	@Override
	public String toString() {
		return "PricingRule [sku=" + sku + ", discount=" + discount + "]";
	}
}
