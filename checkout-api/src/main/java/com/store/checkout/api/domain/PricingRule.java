package com.store.checkout.api.domain;

import java.io.Serializable;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class PricingRule implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private ObjectId id;

	private String type;

}
