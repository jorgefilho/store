package com.store.checkout.api.listener.event;

import static com.store.checkout.api.rule.promotion.constants.BeanName.BY_ONE_GET_ONE;
import static com.store.checkout.api.rule.promotion.constants.BeanName.ONE_ITEM_FREE;
import static com.store.checkout.api.rule.promotion.constants.BeanName.PRICE_DISCOUNT;

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