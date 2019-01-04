package com.coolbook.erp.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.coolbook.erp.entity.ProductCategoryEntity;
import com.coolbook.erp.rest.service.ProductCategoryService;

@RestController
public class ProductCategoryController {
	ProductCategoryService productCategoryService;

	@Autowired
	ProductCategoryController(ProductCategoryService productCategoryService) {
		this.productCategoryService = productCategoryService;
	}

	@RequestMapping(value = "saveProductCategory", method = RequestMethod.POST)
	public void saveProductCategory(@RequestBody ProductCategoryEntity productCategory) {
		this.productCategoryService.saveProductCategory(productCategory);
	}

	@RequestMapping(value = "getProductCategoryById/{id}", method = RequestMethod.GET)
	public ResponseEntity<ProductCategoryEntity> getProductCategoryById(@PathVariable("id") long id) {
		return ResponseEntity.ok().body(this.productCategoryService.getProductCategoryById(id));
	}

	@RequestMapping(value = "getAllProductCategory", method = RequestMethod.GET)
	public List<ProductCategoryEntity> getAllProductCategory() {
		return this.productCategoryService.getAllProductCategory();
	}
}
