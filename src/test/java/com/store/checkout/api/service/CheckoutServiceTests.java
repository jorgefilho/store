package com.store.checkout.api.service;

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
	public void addItem_whenItemIsValid_shouldReturnValidMessageResponse() {
		final CheckoutItem checkoutItem = new CheckoutItem();
		checkoutItem.setSku("mbp");
		checkoutItem.setQuantity(1);

		final MessageResponse messageResponse = checkoutService.addItem(checkoutItem);
	}

}
