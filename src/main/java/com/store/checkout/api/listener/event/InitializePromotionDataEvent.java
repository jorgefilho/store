package com.store.checkout.api.listener.event;

import static com.store.checkout.api.repository.domain.enums.PromotionType.BY_ONE_GET_ONE;
import static com.store.checkout.api.repository.domain.enums.PromotionType.ONE_ITEM_FREE;
import static com.store.checkout.api.repository.domain.enums.PromotionType.PRICE_DISCOUNT;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.store.checkout.api.repository.PromotionRepository;
import com.store.checkout.api.repository.domain.PricingRule;
import com.store.checkout.api.repository.domain.Promotion;

@Component
public class InitializePromotionDataEvent {

	@Autowired
	private PromotionRepository promotionRepository;

	public void setEventFired() {
		promotionRepository.save(new Promotion("atv", ONE_ITEM_FREE, 3));
		promotionRepository.save(new Promotion("ipd", PRICE_DISCOUNT, 4, new PricingRule(BigDecimal.valueOf(50.00))));
		promotionRepository.save(new Promotion("mbp", BY_ONE_GET_ONE, 1, new PricingRule("vga")));
	}
}