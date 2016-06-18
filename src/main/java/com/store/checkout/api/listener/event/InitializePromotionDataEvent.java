package com.store.checkout.api.listener.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.store.checkout.api.repository.PromotionRepository;
import com.store.checkout.api.repository.domain.Promotion;
import com.store.checkout.api.repository.domain.enums.PromotionType;

@Component
public class InitializePromotionDataEvent {

	@Autowired
	private PromotionRepository promotionRepository;

	public void setEventFired() {
		promotionRepository.save(new Promotion("atv", PromotionType.ONE_ITEM_FREE, 3));
		promotionRepository.save(new Promotion("ipd", PromotionType.PRICE_DISCOUNT, 4));
		promotionRepository.save(new Promotion("mbp", PromotionType.BY_ONE_GET_ONE, 1));
	}
}
