package com.store.checkout.api.shoppingcart.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.store.checkout.api.StoreApiApplication;
import com.store.checkout.api.repository.domain.Product;
import com.store.checkout.api.service.shoppingcart.ShoppingCartService;
import com.store.checkout.api.service.shoppingcart.domain.ShoppingCart;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = StoreApiApplication.class)
@WebAppConfiguration
public class ShoppingCartServiceTests {

	@Autowired
	private ShoppingCartService shopppingCartService;

	@Test
	public void addItem_() {
		final Product product = new Product("atv", "Apple TV", BigDecimal.valueOf(109.50));

		final ShoppingCart shoppingCart = shopppingCartService.addItem(product, 1);

		assertNotNull(shoppingCart);
		assertFalse(shoppingCart.getItems().isEmpty());
		assertEquals(1, shoppingCart.getItems().size());
	}

}
