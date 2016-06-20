package com.store.checkout.api.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.store.checkout.api.StoreApiApplication;
import com.store.checkout.api.controller.contract.CheckoutItem;
import com.store.checkout.api.controller.domain.MessageResponse;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = StoreApiApplication.class)
@WebAppConfiguration
public class CheckoutServiceTests {

	@Autowired
	private CheckoutService checkoutService;

	@Test
	public void addItem_whenItemIsValid_shouldReturnValidMessageResponseWithoutErrors() {
		final CheckoutItem checkoutItem = new CheckoutItem();
		checkoutItem.setSku("mbp");
		checkoutItem.setQuantity(1);

		final MessageResponse messageResponse = checkoutService.addItem(checkoutItem);

		assertNotNull(messageResponse);
		assertFalse(messageResponse.isError());
		assertNotNull(messageResponse.getEntity());
	}

	@Test
	public void addItem_whenItemIsValidButNotExists_shouldReturnValidMessageResponseWithError() {
		final CheckoutItem checkoutItem = new CheckoutItem();
		checkoutItem.setSku("xpto");
		checkoutItem.setQuantity(1);

		final MessageResponse messageResponse = checkoutService.addItem(checkoutItem);

		assertNotNull(messageResponse);
		assertTrue(messageResponse.isError());
	}

	@Test
	public void addItem_whenItemIsEmpty_shouldReturnValidMessageResponseWithError() {

		final MessageResponse messageResponse = checkoutService.addItem(new CheckoutItem());

		assertNotNull(messageResponse);
		assertTrue(messageResponse.isError());
	}

	@Test(expected = IllegalArgumentException.class)
	public void addItem_whenItemIsNull_shouldReturnValidMessageResponseWithError() {
		checkoutService.addItem(null);
	}

	@Test
	public void removeItem_whenItemIsValid_shouldReturnValidMessageResponseWithoutErrors() {
		final CheckoutItem checkoutItem = new CheckoutItem();
		final String sku = "mbp";
		checkoutItem.setSku(sku);
		checkoutItem.setQuantity(1);

		MessageResponse messageResponse = checkoutService.addItem(checkoutItem);

		assertNotNull(messageResponse);
		assertFalse(messageResponse.isError());
		assertNotNull(messageResponse.getEntity());

		messageResponse = checkoutService.removeItem(sku);

		assertNotNull(messageResponse);
		assertFalse(messageResponse.isError());
		assertNotNull(messageResponse.getEntity());

	}
}
