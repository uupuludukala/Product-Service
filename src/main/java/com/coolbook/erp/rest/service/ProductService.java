package com.coolbook.erp.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coolbook.erp.entity.ProductEntity;
import com.coolbook.erp.repository.ProductRepository;

@Service
public class ProductService {

	ProductRepository productRepository;
	
	@Autowired
	ProductService(ProductRepository productRepository){
		this.productRepository=productRepository;
	}
	public void saveProduct(ProductEntity product) {
		this.productRepository.save(product);
	}
	public ProductEntity getProductById(long id) {
		return this.productRepository.getOne(id);
	}
	public List<ProductEntity> getAllProduct(){
		return this.productRepository.findAll();
	}
}
