package com.store.checkout.api.controller.contract;

import java.io.Serializable;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;

public class CheckoutItem implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotBlank
	private String sku;

	@Min(value = 1)
	private int quantity;

}
