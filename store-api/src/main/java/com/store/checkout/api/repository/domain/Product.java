package com.store.checkout.api.repository.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String sku;

	private String name;

	private BigDecimal price;

	public Product(String sku, String name, BigDecimal price) {
		this.sku = sku;
		this.name = name;
		this.price = price;
	}

	public final String getSku() {
		return sku;
	}

	public final String getName() {
		return name;
	}

	public final BigDecimal getPrice() {
		return price;
	}

	@Override
	public String toString() {
		return "Product [sku=" + sku + ", name=" + name + ", price=" + price + "]";
	}
}