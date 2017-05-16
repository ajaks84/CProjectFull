package com.deshand.adc.web.controller;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.deshand.adc.datamodel.Product;
import com.deshand.adc.services.services.IProductService;
import com.deshand.adc.web.model.ProductModel;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Inject
	private IProductService service;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<ProductModel> getById(@PathVariable Integer id) {
		Product product = service.get(id);
		return new ResponseEntity<ProductModel>(entity2model(product), HttpStatus.OK);
	}

	private ProductModel entity2model(Product product) {
		ProductModel e = new ProductModel();
		e.setName(product.getName());
		e.setId(product.getId());
		e.setGroup(product.getGroupe());
		return e;
	}
	
	

}
