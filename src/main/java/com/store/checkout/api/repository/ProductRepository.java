package com.store.checkout.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.store.checkout.api.repository.domain.Product;

public interface ProductRepository extends MongoRepository<Product, String> {

	@Query("{ 'sku' : ?0 }")
	Product findBySku(String sku);
}
