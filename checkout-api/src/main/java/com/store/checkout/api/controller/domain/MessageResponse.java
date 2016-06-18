package com.store.checkout.api.controller.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpHeaders;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class MessageResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String uuid;
	private Object entity;
	private HttpHeaders httpHeaders = new HttpHeaders();
	private List<Message> messages = new ArrayList<>();
	private boolean error;
	
	public MessageResponse() {
		this.uuid = UUID.randomUUID().toString();
	}
	
	public String getUuid() {
		return uuid;
	}
	
	public Object getEntity() {
		return entity;
	}
	
	public void setEntity(Object entity) {
		this.entity = entity;
	}

	@JsonIgnore
	public HttpHeaders getHttpHeaders() {
		return httpHeaders;
	}
	
	public void setHttpHeaders(HttpHeaders httpHeaders) {
		this.httpHeaders = httpHeaders;
	}
	
	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
	
	public boolean isError() {
		return error;
	}
	
	public void setError(boolean error) {
		this.error = error;
	}

	@Override
	public String toString() {
		return "MessageResponse [uuid=" + uuid + ", entity=" + entity
				+ ", httpHeaders=" + httpHeaders + ", messages=" + messages
				+ ", error=" + error + "]";
	}

}
