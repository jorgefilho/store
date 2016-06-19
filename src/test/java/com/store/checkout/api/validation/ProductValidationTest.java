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

	private static final String INVALID_SKU = "sku-error";

	private static final String VALID_SKU = "xpto";

	private static final int EXPECTED_VIOLATION_SIZE_TO_EMPTY_OBJECT = 2;

	private static final int EXPECTED_VIOLATION_SIZE_TO_INCORRECT_OBJECT = 1;

	@Mock
	private ProductRepository productRepository;

	@InjectMocks
	private ProductValidation productValidation = new ProductValidation();
	
	@Test(expected = IllegalArgumentException.class)
	public void validate_whenParameterIsNull_shouldReturnIllegalArgumentException() {
		final CheckoutItem checkoutItem = null;
		productValidation.validate(checkoutItem);
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
		item.setSku(VALID_SKU);

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
	public void validate_whenCheckoutItemIsValidButNotFoundInRepository_shouldReturnMessageError() {
		doReturn(null).when(productRepository).findBySku(INVALID_SKU);
		final CheckoutItem item = new CheckoutItem();

		item.setSku(INVALID_SKU);
		item.setQuantity(1);

		final Map<String, String> errors = productValidation.validate(item);
		assertNotNull(errors);
		assertFalse(errors.isEmpty());
		assertEquals("Product sku not found.", errors.values().iterator().next());
	}

	@Test()
	public void validate_whenCheckoutItemIsValid_shouldReturnZeroErrors() {
		doReturn(new Product(VALID_SKU, "name", BigDecimal.TEN)).when(productRepository).findBySku(VALID_SKU);
		final CheckoutItem item = new CheckoutItem();

		item.setSku(VALID_SKU);
		item.setQuantity(1);

		final Map<String, String> errors = productValidation.validate(item);
		assertNotNull(errors);
		assertTrue(errors.isEmpty());
		assertEquals(0, errors.size());
	}

	@Test()
	public void validate_whenSkuIsNull_shouldReturnErrorMessage() {
		final String sku = null;
		final Map<String, String> errors = productValidation.validate(sku);

		assertNotNull(errors);
		assertFalse(errors.isEmpty());
		assertEquals("Product sku invalid.", errors.values().iterator().next());
	}

	@Test()
	public void validate_whenSkuIsEmpty_shouldReturnErrorMessage() {
		final String sku = null;
		final Map<String, String> errors = productValidation.validate(sku);

		assertNotNull(errors);
		assertFalse(errors.isEmpty());
		assertEquals("Product sku invalid.", errors.values().iterator().next());
	}

	@Test()
	public void validate_whenSkuIsValidButNotFoundInRepository_shouldReturnZeroErrors() {
		doReturn(null).when(productRepository).findBySku(INVALID_SKU);

		final Map<String, String> errors = productValidation.validate(INVALID_SKU);
		assertNotNull(errors);
		assertFalse(errors.isEmpty());
		assertEquals("Product sku not found.", errors.values().iterator().next());
	}

	@Test()
	public void validate_whenSkuIsValid_shouldReturnZeroErrors() {
		doReturn(new Product(VALID_SKU, "name", BigDecimal.TEN)).when(productRepository).findBySku(VALID_SKU);

		final Map<String, String> errors = productValidation.validate(VALID_SKU);
		assertNotNull(errors);
		assertTrue(errors.isEmpty());
		assertEquals(0, errors.size());
	}
}