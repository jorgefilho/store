package com.store.checkout.api.repository.domain;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.store.checkout.api.repository.domain.enums.PromotionType;

@Document
public class Promotion implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	private String sku;

	private PromotionType type;

	private int numberOfItems;

	private boolean active;

	public Promotion(final String sku, final PromotionType type, final int numberOfItems) {
		this.sku = sku;
		this.type = type;
		this.numberOfItems = numberOfItems;
		this.active = true;
	}

	public String getSku() {
		return sku;
	}

	public PromotionType getType() {
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

	@Override
	public String toString() {
		return "Promotion [sku=" + sku + ", type=" + type + ", numberOfItems=" + numberOfItems + ", active=" + active
				+ "]";
	}
}
