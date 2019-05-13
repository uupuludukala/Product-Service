package com.coolbook.erp.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.coolbook.erp.entity.ProductEntity;
import com.coolbook.erp.repository.ProductRepository;
import com.coolbook.erp.repository.specs.ProductSpecification;
import com.coolbook.erp.rest.searchCriteria.ProductCriteria;

@Service
public class ProductService {

	ProductRepository productRepository;

	@Autowired
	ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public long saveProduct(ProductEntity product) {
		return this.productRepository.save(product).getId();
	}
	
	public void updateProduct(ProductEntity product,long id) {
		product.setId(id);
		this.productRepository.save(product);
	}
	
	public void deleteProduct(long id) {
		
		this.productRepository.delete(id);
	}

	public ProductEntity getProductById(long id) {
		return this.productRepository.getOne(id);
	}

	public Page<ProductEntity> getAllProduct(Pageable page, ProductCriteria criteria) {
		ProductSpecification specification = new ProductSpecification(criteria);
		return this.productRepository.findAll(specification, page);
	}
}
