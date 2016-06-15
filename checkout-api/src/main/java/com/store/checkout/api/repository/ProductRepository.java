package com.store.checkout.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.store.checkout.api.domain.Product;

public interface ProductRepository extends MongoRepository<Product, String> {
}
