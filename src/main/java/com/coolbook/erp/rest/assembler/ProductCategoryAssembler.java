package com.coolbook.erp.rest.assembler;

import com.coolbook.erp.common.enums.StatusEnum;
import com.coolbook.erp.model.ProductCategoryGet;
import com.coolbook.erp.model.ProductCategoryPost;
import org.springframework.stereotype.Component;

import com.coolbook.erp.entity.ProductCategoryEntity;

@Component
public class ProductCategoryAssembler {
	public ProductCategoryGet essembleProductCategoryGet(ProductCategoryEntity productCategoryEntity) {
		ProductCategoryGet productCategoryGet = new ProductCategoryGet();
		productCategoryGet.setProductCatCode(productCategoryEntity.getProductCategoryCode());
		productCategoryGet.setParentCategory(productCategoryEntity.getParentCategory());
		productCategoryGet.setProductCategory_id(productCategoryEntity.getId());
		productCategoryGet.setProductCatName(productCategoryEntity.getProductCategoryName());
		productCategoryGet.setStatus(StatusEnum.getByCode(productCategoryEntity.getStatus()));
		return productCategoryGet;
	}

	public ProductCategoryEntity essembleProductCategoryentity(ProductCategoryPost productCategoryPost) {
		ProductCategoryEntity productCategoryEntity = new ProductCategoryEntity();
		productCategoryEntity.setProductCategoryCode(productCategoryPost.getProductCatCode());
		productCategoryEntity.setProductCategoryName(productCategoryPost.getProductCatName());
		productCategoryEntity.setParentCategory(productCategoryPost.getParentCategory());
		if(productCategoryPost.getStatus()!=null)
		    productCategoryEntity.setStatus(productCategoryPost.getStatus().getCode());
		return productCategoryEntity;
	}
}
