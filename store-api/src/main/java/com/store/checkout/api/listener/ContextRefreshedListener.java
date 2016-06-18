package com.store.checkout.api.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.store.checkout.api.listener.event.InitializeProductDataEvent;

@Component
public class ContextRefreshedListener implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private InitializeProductDataEvent initializeProductDataEvent;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
		initializeProductDataEvent.setEventFired();

	}

}
