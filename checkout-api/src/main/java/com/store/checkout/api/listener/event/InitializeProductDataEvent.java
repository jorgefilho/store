package com.store.checkout.api.listener.event;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.store.checkout.api.repository.ProductRepository;
import com.store.checkout.api.repository.domain.Product;

@Component
public class InitializeProductDataEvent {
	@Autowired
	private ProductRepository productRepository;

	private boolean eventFired = false;

	public Boolean getEventFired() {
		return eventFired;
	}
	public void setEventFired() {
		productRepository.save(new Product("ipd", "Super Ipad", BigDecimal.valueOf(549.99)));
		productRepository.save(new Product("mpb", "MacBook Pro", BigDecimal.valueOf(1399.99)));
		productRepository.save(new Product("atv", "Apple TV", BigDecimal.valueOf(109.50)));
		productRepository.save(new Product("vga", "VGA adapter", BigDecimal.valueOf(30.00)));
		this.eventFired = true;
	}
}
