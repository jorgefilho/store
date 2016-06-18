package com.store.checkout.api.validation;

import static javax.validation.Validation.buildDefaultValidatorFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.store.checkout.api.controller.contract.CheckoutItem;
import com.store.checkout.api.repository.ProductRepository;
import com.store.checkout.api.repository.domain.Product;

@Component
public class ProductValidation {

	@Autowired
	private ProductRepository productRepository;

	public Map<String, String> validate(final CheckoutItem item) {

		final Set<ConstraintViolation<CheckoutItem>> itemValidation = buildDefaultValidatorFactory().getValidator()
				.validate(item);

		final Map<String, String> errors = new HashMap<String, String>();

		if (itemValidation.isEmpty()) {

			final Product productFound = productRepository.findBySku(item.getSku());
			if (productFound == null) {
				errors.put("sku", "Product sku not found.");
			}
		} else {
			itemValidation.forEach(violation -> {
				violation.getPropertyPath().forEach(propertyPath -> {
					errors.put(propertyPath.getName(), violation.getMessage());
				});
			});
		}
		return errors;
	}
}
