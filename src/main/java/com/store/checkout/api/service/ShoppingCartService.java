package com.store.checkout.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.checkout.api.repository.domain.Product;
import com.store.checkout.api.session.domain.ShoppingCart;

@Service
public class ShoppingCartService {

	@Autowired
	private SessionService sessionService;

	public ShoppingCart addItem(final Product product) {

		return null;
	}


}
