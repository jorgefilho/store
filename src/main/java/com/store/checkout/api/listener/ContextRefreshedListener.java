package com.store.checkout.api.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.store.checkout.api.listener.event.InitializeProductDataEvent;
import com.store.checkout.api.listener.event.InitializePromotionDataEvent;

@Component
public class ContextRefreshedListener implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private InitializeProductDataEvent initializeProductDataEvent;

	@Autowired
	private InitializePromotionDataEvent initializePromotionDataEvent;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

		initializeProductDataEvent.setEventFired();
		initializePromotionDataEvent.setEventFired();
	}
}
