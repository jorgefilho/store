package com.store.checkout.api.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.checkout.api.controller.contract.Cart;
import com.store.checkout.api.controller.contract.CheckoutItem;
import com.store.checkout.api.controller.domain.MessageResponse;
import com.store.checkout.api.controller.domain.builder.MessageReponseBuilder;
import com.store.checkout.api.repository.ProductRepository;
import com.store.checkout.api.repository.domain.Product;
import com.store.checkout.api.service.shoppingcart.ShoppingCartService;
import com.store.checkout.api.service.shoppingcart.domain.ShoppingCart;
import com.store.checkout.api.validation.ProductValidation;

@Service
public class CheckoutService {

	@Autowired
	private ProductValidation productValidation;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ShoppingCartService shippingCartService;

	public MessageResponse addItem(final CheckoutItem checkoutItem) {

		final MessageReponseBuilder messageResponseBilder = new MessageReponseBuilder();

		final Map<String, String> errors = productValidation.validate(checkoutItem);

		if (errors.isEmpty()) {

			final Product product = productRepository.findBySku(checkoutItem.getSku());

			final ShoppingCart shoppingCart = shippingCartService.addItem(product, checkoutItem.getQuantity());

			messageResponseBilder.entity(new Cart(shoppingCart.getItems(), shoppingCart.getTotal()));
			messageResponseBilder.addMessage("checkout", "Item added with success!");

		} else {
			messageResponseBilder.errors(errors);
		}
		return messageResponseBilder.build();
	}

	public MessageResponse removeItem(final String sku) {
		final MessageReponseBuilder messageResponseBilder = new MessageReponseBuilder();

		final Map<String, String> errors = productValidation.validate(sku);

		if (errors.isEmpty()) {
			final ShoppingCart shoppingCart = shippingCartService.removeItem(sku);

			messageResponseBilder.entity(new Cart(shoppingCart.getItems(), shoppingCart.getTotal()));
			messageResponseBilder.addMessage("checkout", "Item removed with success!");

		} else {
			messageResponseBilder.errors(errors);
		}
		return messageResponseBilder.build();
	}

	public MessageResponse getItems() {
		final MessageReponseBuilder messageResponseBilder = new MessageReponseBuilder();

		final ShoppingCart shoppingCart = shippingCartService.removeAllItems();

		messageResponseBilder.entity(new Cart(shoppingCart.getItems(), shoppingCart.getTotal()));
		messageResponseBilder.addMessage("checkout", "All items removed with success!");

		return messageResponseBilder.build();
	}

	public MessageResponse removeAllItems() {
		final MessageReponseBuilder messageResponseBilder = new MessageReponseBuilder();

		final ShoppingCart shoppingCart = shippingCartService.removeAllItems();

		messageResponseBilder.entity(new Cart(shoppingCart.getItems(), shoppingCart.getTotal()));
		messageResponseBilder.addMessage("checkout", "All items removed with success!");

		return messageResponseBilder.build();
	}
}