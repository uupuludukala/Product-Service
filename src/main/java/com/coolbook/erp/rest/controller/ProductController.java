package com.coolbook.erp.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.coolbook.erp.entity.ProductEntity;
import com.coolbook.erp.rest.service.ProductService;




@RestController
public class ProductController {
ProductService productService;
@Autowired
	ProductController(ProductService productService){
		this.productService=productService;
	}
	@RequestMapping(value="saveProduct",method=RequestMethod.POST)
	public void saveProduct(@RequestBody ProductEntity product) {
		this.productService.saveProduct(product);
	}
	@RequestMapping(value="getProductById/{id}",method=RequestMethod.GET)
	public ResponseEntity<ProductEntity> getProductById(@PathVariable ("id") long id){
		return ResponseEntity.ok().body(this.productService.getProductById(id));
	}
	
	@RequestMapping(value="getAllProduct",method=RequestMethod.GET)
	public List<ProductEntity> getAllProduct(){
		return this.productService.getAllProduct();
	}
}
