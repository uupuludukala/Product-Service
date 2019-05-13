package com.coolbook.erp.rest.assembler;

import org.springframework.stereotype.Component;

import com.coolbook.erp.entity.ProductCategoryEntity;
import com.coolbook.model.ProductCategoryGet;
import com.coolbook.model.ProductCategoryPost;

@Component
public class ProductCategoryAssembler {
	public ProductCategoryGet essembleProductCategoryGet(ProductCategoryEntity productCategoryEntity) {
		ProductCategoryGet productCategoryGet = new ProductCategoryGet();
		productCategoryGet.setProductCatCode(productCategoryEntity.getProductcatCode());
		productCategoryGet.setParentCategory(productCategoryEntity.getParentCategory());
		productCategoryGet.setProductCategory_id(productCategoryEntity.getId());
		productCategoryGet.setProductCatName(productCategoryEntity.getProductcatName());
		return productCategoryGet;
	}

	public ProductCategoryEntity essembleProductCategoryentity(ProductCategoryPost productCategoryPost) {
		ProductCategoryEntity productCategoryEntity = new ProductCategoryEntity();
		productCategoryEntity.setProductcatCode(productCategoryPost.getProductCatCode());
		productCategoryEntity.setProductcatName(productCategoryPost.getProductCatName());
		productCategoryEntity.setParentCategory(productCategoryPost.getParentCategory());
		return productCategoryEntity;
	}
}
