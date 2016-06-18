package com.store.checkout.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.checkout.api.repository.PromotionRepository;
import com.store.checkout.api.repository.domain.Promotion;

@Service
public class PromotionService {

	@Autowired
	private PromotionRepository promotionRepository;

	public List<Promotion> getAll() {
		return promotionRepository.findAll();
	}

	public Promotion getBySku(final String sku) {
		return promotionRepository.findBySku(sku);
	}
}
