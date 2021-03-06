package com.store.checkout.api.repository.domain;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Promotion implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	private String sku;

	private String type;

	private int numberOfItems;

	private boolean active;

	private PricingRule pricingRule = null;

	@PersistenceConstructor
	public Promotion(final String sku, final String type, final int numberOfItems) {
		this.sku = sku;
		this.type = type;
		this.numberOfItems = numberOfItems;
		this.active = true;
	}

	public Promotion(final String sku, final String type, final int numberOfItems,
			final PricingRule pricingRule) {
		this.sku = sku;
		this.type = type;
		this.numberOfItems = numberOfItems;
		this.active = true;
		this.pricingRule = pricingRule;
	}

	public String getSku() {
		return sku;
	}

	public String getType() {
		return type;
	}

	public int getNumberOfItems() {
		return numberOfItems;
	}


	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public PricingRule getPricingRule() {
		return pricingRule;
	}

	@Override
	public String toString() {
		return "Promotion [sku=" + sku + ", type=" + type + ", numberOfItems=" + numberOfItems + ", active=" + active
				+ ", pricingRule=" + pricingRule + "]";
	}
}
