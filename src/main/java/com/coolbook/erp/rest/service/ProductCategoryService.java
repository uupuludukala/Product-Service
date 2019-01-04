package com.coolbook.erp.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coolbook.erp.entity.ProductCategoryEntity;
import com.coolbook.erp.repository.ProductCategoryRepository;

@Service
public class ProductCategoryService {
	ProductCategoryRepository productCategoryRepository;

	@Autowired
	ProductCategoryService(ProductCategoryRepository productCategoryRepository) {
		this.productCategoryRepository = productCategoryRepository;
	}

	public void saveProductCategory(ProductCategoryEntity productCategory) {
		this.productCategoryRepository.save(productCategory);
	}

	public ProductCategoryEntity getProductCategoryById(long id) {
		return this.productCategoryRepository.getOne(id);
	}

	public List<ProductCategoryEntity> getAllProductCategory() {
		return this.productCategoryRepository.findAll();
	}
}
