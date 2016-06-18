package com.store.checkout.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.store.checkout.api.controller.constant.ControllerPath;
import com.store.checkout.api.repository.domain.Product;
import com.store.checkout.api.service.ProductService;

@RestController
@RequestMapping(value = ControllerPath.PRODUCT_PATH)
public class ProductController {

	@Autowired
	private ProductService productService;

	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)

	public @ResponseBody ResponseEntity<List<Product>> getAll() {
		final List<Product> products = productService.getAll();
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}
}
