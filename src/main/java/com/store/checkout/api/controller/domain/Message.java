package com.store.checkout.api.controller.domain;

import java.io.Serializable;

public class Message implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String origin;
	private String description;
	
	public Message(final String origin, final String description) {
		this.origin = origin;
		this.description = description;
	}

	public String getOrigin() {
		return origin;
	}
	
	public String getDescription() {
		return description;
	}

	@Override
	public String toString() {
		return "Message [origin=" + origin + ", description=" + description + "]";
	}

}