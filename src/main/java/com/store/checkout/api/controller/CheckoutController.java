package com.store.checkout.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.store.checkout.api.controller.constants.ControllerPath;
import com.store.checkout.api.controller.contract.CheckoutItem;
import com.store.checkout.api.controller.domain.MessageResponse;
import com.store.checkout.api.service.CheckoutService;

@RestController
@RequestMapping(value = ControllerPath.CHECKOUT_PATH)
public class CheckoutController {

	@Autowired
	private CheckoutService checkoutService;

	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<MessageResponse> addItem(@RequestBody final CheckoutItem checkoutItem) {
		final MessageResponse messageResponse = this.checkoutService.addItem(checkoutItem);
		final HttpHeaders httpHeaders = messageResponse.getHttpHeaders();
		return new ResponseEntity<MessageResponse>(messageResponse, httpHeaders, HttpStatus.OK);
	}

	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value = "/{sku}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<MessageResponse> removeItem(@PathVariable(value = "sku") final String sku) {
		final MessageResponse messageResponse = this.checkoutService.removeItem(sku);
		final HttpHeaders httpHeaders = messageResponse.getHttpHeaders();
		return new ResponseEntity<MessageResponse>(messageResponse, httpHeaders, HttpStatus.OK);
	}

	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<MessageResponse> removeAllItems() {
		final MessageResponse messageResponse = this.checkoutService.removeAllItems();
		final HttpHeaders httpHeaders = messageResponse.getHttpHeaders();
		return new ResponseEntity<MessageResponse>(messageResponse, httpHeaders, HttpStatus.OK);
	}

	@ResponseStatus(HttpStatus.ACCEPTED)
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<MessageResponse> getItems() {
		final MessageResponse messageResponse = this.checkoutService.getItems();
		final HttpHeaders httpHeaders = messageResponse.getHttpHeaders();
		return new ResponseEntity<MessageResponse>(messageResponse, httpHeaders, HttpStatus.OK);
	}
}
