package com.store.checkout.api.configuration;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.store.checkout.api.domain.Product;
import com.store.checkout.api.repository.ProductRepository;

@Configuration
public class DataLoaderConfiguration {

	@Autowired
	private ProductRepository productRepository;

	public void loadProducts() {
		productRepository.save(new Product("ipd", "Super Ipad", BigDecimal.valueOf(549.99)));
		productRepository.save(new Product("mpb", "MacBook Pro", BigDecimal.valueOf(1399.99)));
		productRepository.save(new Product("atv", "Apple TV", BigDecimal.valueOf(109.50)));
		productRepository.save(new Product("vga", "VGA adapter", BigDecimal.valueOf(30.00)));
	}

}
