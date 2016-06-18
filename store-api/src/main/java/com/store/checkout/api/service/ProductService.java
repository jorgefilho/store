package com.store.checkout.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.checkout.api.repository.ProductRepository;
import com.store.checkout.api.repository.domain.Product;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public List<Product> getAll() {
		return productRepository.findAll();
	}
}
