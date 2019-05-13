package com.coolbook.erp.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.coolbook.erp.entity.ProductCategoryEntity;
import com.coolbook.erp.entity.ProductEntity;
import com.coolbook.erp.repository.ProductCategoryRepository;
import com.coolbook.erp.repository.specs.ProductCategorySpecification;
import com.coolbook.erp.rest.searchCriteria.ProductCategoryCriteria;

@Service
public class ProductCategoryService {
	ProductCategoryRepository productCategoryRepository;

	@Autowired
	ProductCategoryService(ProductCategoryRepository productCategoryRepository) {
		this.productCategoryRepository = productCategoryRepository;
	}

	public long saveProductCategory(ProductCategoryEntity productCategory) {
		return this.productCategoryRepository.save(productCategory).getId();
	}

	public void updateProductCategory(ProductCategoryEntity productcategory,long id) {
		productcategory.setId(id);
		this.productCategoryRepository.save(productcategory);
	}
	
	public void deleteProductCategory(long id) {
		
		this.productCategoryRepository.delete(id);
	}
	
	public ProductCategoryEntity getProductCategoryById(long id) {
		return this.productCategoryRepository.getOne(id);
	}

	public Page<ProductCategoryEntity> getAllProductCategory(Pageable page, ProductCategoryCriteria criteria) {
		ProductCategorySpecification specification = new ProductCategorySpecification(criteria);
		return this.productCategoryRepository.findAll(specification, page);
	}
}
