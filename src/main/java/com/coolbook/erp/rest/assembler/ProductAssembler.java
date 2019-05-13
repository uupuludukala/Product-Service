package com.coolbook.erp.rest.assembler;

import org.springframework.stereotype.Component;

import com.coolbook.erp.entity.ProductCategoryEntity;
import com.coolbook.erp.entity.ProductEntity;
import com.coolbook.model.ProductGet;
import com.coolbook.model.ProductPost;

@Component
public class ProductAssembler {
	public ProductGet essembleProductGet(ProductEntity productEntity) {
		ProductGet productGet = new ProductGet();
		productGet.setProduct_Id(productEntity.getId());
		productGet.setActive(productEntity.isActive());
		productGet.setBarcode(productEntity.getBarcode());
		productGet.setCost(productEntity.getCost());
		productGet.setProductCategory(productEntity.getProductCategory().getId());
		productGet.setProductCode(productEntity.getProductCode());
		productGet.setProductName(productEntity.getProductName());
		productGet.setProductType(productEntity.getProductType());
		productGet.setQuantity(productEntity.getQuantity());
		productGet.setSalePrice(productEntity.getSalePrice());
		productGet.setImageUrl(productEntity.getImageUrl());
		return productGet;
	}

	public ProductEntity essembleProductentity(ProductPost productPost) {
		ProductEntity productEntity = new ProductEntity();
		productEntity.setActive(productPost.isActive());
		productEntity.setBarcode(productPost.getBarcode());
		productEntity.setCost(productPost.getCost());
		ProductCategoryEntity productCategory = new ProductCategoryEntity();
		productCategory.setId(productPost.getProductCategory());
		productEntity.setProductCategory(productCategory);
		productEntity.setProductCode(productPost.getProductCode());
		productEntity.setProductName(productPost.getProductName());
		productEntity.setProductType(productPost.getProductType());
		productEntity.setQuantity(productPost.getQuantity());
		productEntity.setSalePrice(productPost.getSalePrice());
		productEntity.setImageUrl(productPost.getImageUrl());
		return productEntity;
	}
}
