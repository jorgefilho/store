package com.store.checkout.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.store.checkout.api.controller.constants.ControllerPath;
import com.store.checkout.api.controller.contract.CheckoutItem;
import com.store.checkout.api.service.CheckoutService;

@RestController
@RequestMapping(value = ControllerPath.CHECKOUT_PATH)
public class CheckoutController {

	@Autowired
	private CheckoutService checkoutService;

	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	
	public @ResponseBody ResponseEntity<String> addItem(@RequestBody final CheckoutItem checkoutItem) {
		this.checkoutService.addItem(checkoutItem);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
}
