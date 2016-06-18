package com.store.checkout.api.controller.domain.builder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import com.store.checkout.api.controller.domain.Message;
import com.store.checkout.api.controller.domain.MessageResponse;

@Component
public class MessageReponseBuilder implements Serializable {

	private static final long serialVersionUID = 1L;
	private Object entity;
	private HttpHeaders httpHeaders = new HttpHeaders();
	private List<Message> messages = new ArrayList<>();
	private boolean error = false;

	public MessageReponseBuilder entity(final Object entity) {
		this.entity = entity;
		return this;
	}
	
	public HttpHeaders addHttpHeader(final String key, final String value) {
		this.httpHeaders.add(key, value);
		return httpHeaders;
	}

	public MessageReponseBuilder messages(final Map<String, String> messages) {
		Optional.of(messages).orElse(new HashMap<String, String>())
				.entrySet().stream()
				.forEach(entrySet -> addMessage(entrySet.getKey(), entrySet.getValue()));
		return this;
	}
	
	public MessageReponseBuilder errors(final Map<String, String> errors) {
		Optional.of(errors).orElse(new HashMap<String, String>())
				.entrySet().stream()
				.forEach(entrySet -> addError(entrySet.getKey(), entrySet.getValue()));
		return this;
	}
	
	public MessageReponseBuilder addMessage(final String origin, final String message) {
		this.messages.add(new Message(origin, message));
		return this;
	}
	
	public MessageReponseBuilder addError(final String origin, final String message) {
		this.messages.add(new Message(origin, message));
		this.error = true;
		return this;
	}
	
	public MessageResponse build() {
		final MessageResponse messageResponse = new MessageResponse();
		messageResponse.setHttpHeaders(this.httpHeaders);
		messageResponse.setMessages(this.messages);
		messageResponse.setEntity(this.entity);
		messageResponse.setError(this.error);
		return messageResponse;
	}
}