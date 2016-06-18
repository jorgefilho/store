package com.store.checkout.api.validation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;

import java.math.BigDecimal;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.store.checkout.api.controller.contract.CheckoutItem;
import com.store.checkout.api.repository.ProductRepository;
import com.store.checkout.api.repository.domain.Product;

@RunWith(MockitoJUnitRunner.class)
public class ProductValidationTest {

	private static final String SKU_VALID = "xpto";

	private static final int EXPECTED_VIOLATION_SIZE_TO_EMPTY_OBJECT = 2;

	private static final int EXPECTED_VIOLATION_SIZE_TO_INCORRECT_OBJECT = 1;

	@Mock
	private ProductRepository productRepository;

	@InjectMocks
	private ProductValidation productValidation = new ProductValidation();
	
	@Test(expected = IllegalArgumentException.class)
	public void validate_whenParameterIsNull_shouldReturnIllegalArgumentException() {
		productValidation.validate(null);
	}

	@Test()
	public void validate_whenParameterIsEmpty_shouldReturnExpectedViolation() {
		final Map<String, String> errors = productValidation.validate(new CheckoutItem());
		assertNotNull(errors);
		assertFalse(errors.isEmpty());
		assertEquals(EXPECTED_VIOLATION_SIZE_TO_EMPTY_OBJECT, errors.size());
	}

	@Test()
	public void validate_whenCheckoutItemIsIncompleteWithoutQuantity_shouldReturnExpectedViolation() {
		final CheckoutItem item = new CheckoutItem();
		item.setSku(SKU_VALID);

		final Map<String, String> errors = productValidation.validate(item);
		assertNotNull(errors);
		assertFalse(errors.isEmpty());
		assertEquals(EXPECTED_VIOLATION_SIZE_TO_INCORRECT_OBJECT, errors.size());
	}

	@Test()
	public void validate_whenCheckoutItemIsIncompleteWithoutSku_shouldReturnExpectedViolation() {
		final CheckoutItem item = new CheckoutItem();
		item.setQuantity(1);

		final Map<String, String> errors = productValidation.validate(item);
		assertNotNull(errors);
		assertFalse(errors.isEmpty());
		assertEquals(EXPECTED_VIOLATION_SIZE_TO_INCORRECT_OBJECT, errors.size());
	}

	@Test()
	public void validate_whenCheckoutItemIsValidButNotFoundInRepository_shouldReturnZeroErrors() {
		doReturn(null).when(productRepository).findBySku(SKU_VALID);
		final CheckoutItem item = new CheckoutItem();

		item.setSku(SKU_VALID);
		item.setQuantity(1);

		final Map<String, String> errors = productValidation.validate(item);
		assertNotNull(errors);
		assertFalse(errors.isEmpty());
		assertEquals("Product sku not found.", errors.values().iterator().next());
	}

	@Test()
	public void validate_whenCheckoutItemIsValid_shouldReturnZeroErrors() {
		doReturn(new Product(SKU_VALID, "name", BigDecimal.TEN)).when(productRepository).findBySku(SKU_VALID);
		final CheckoutItem item = new CheckoutItem();

		item.setSku(SKU_VALID);
		item.setQuantity(1);

		final Map<String, String> errors = productValidation.validate(item);
		assertNotNull(errors);
		assertTrue(errors.isEmpty());
		assertEquals(0, errors.size());
	}
}