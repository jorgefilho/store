package com.store.checkout.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.store.checkout.api.controller.constants.ControllerPath;
import com.store.checkout.api.repository.domain.Promotion;
import com.store.checkout.api.rule.promotion.service.PromotionService;

@RestController
@RequestMapping(value = ControllerPath.PROMOTION_PATH)
public class PromotionController {

	@Autowired
	private PromotionService promotionService;

	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)

	public @ResponseBody ResponseEntity<List<Promotion>> getAll() {
		final List<Promotion> promotions = promotionService.getAll();
		return new ResponseEntity<List<Promotion>>(promotions, HttpStatus.OK);
	}

	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value = "/sku/{sku}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Promotion> getBySku(@PathVariable(value = "sku") String sku) {
		final Promotion promotion = promotionService.getBySku(sku);
		return new ResponseEntity<Promotion>(promotion, HttpStatus.OK);
	}
}
