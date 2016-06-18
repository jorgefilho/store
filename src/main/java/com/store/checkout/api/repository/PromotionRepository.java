package com.store.checkout.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.store.checkout.api.repository.domain.Promotion;

public interface PromotionRepository extends MongoRepository<Promotion, String> {

	@Query("{ 'sku' : ?0 }")
	Promotion findBySku(String sku);
}
