package com.coolbook.erp.rest.assembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.coolbook.erp.entity.ProductCategoryEntity;
import com.coolbook.erp.rest.service.ProductCategoryService;
import com.coolbook.model.ProductCategoryGet;



public abstract class AbstractProductCategoryResourceAssembler
		extends ResourceAssemblerSupport<ProductCategoryEntity, ProductCategoryGet> {
	protected String requestURI;
	
	@Autowired
	ProductCategoryService productCategoryService;

	public AbstractProductCategoryResourceAssembler() {
		super(ProductCategoryEntity.class, ProductCategoryGet.class);
	}

	@Override
	public ProductCategoryGet toResource(ProductCategoryEntity productCategoryEntity) {
		return createProductCategoryJson(productCategoryEntity);
	}

	private ProductCategoryGet createProductCategoryJson(ProductCategoryEntity productCategoryEntity) {
		ProductCategoryGet productCategoryGet = new ProductCategoryGet();
		productCategoryGet.setProductCategory_id(productCategoryEntity.getId());
		productCategoryGet.setParentCategory(productCategoryEntity.getParentCategory());
		productCategoryGet.setParentCategoryCode(productCategoryService.getProductCategoryById(productCategoryEntity.getParentCategory()).getProductcatCode());
		productCategoryGet.setProductCatCode(productCategoryEntity.getProductcatCode());
		productCategoryGet.setProductCatName(productCategoryEntity.getProductcatName());
		return productCategoryGet;
	}

	protected abstract String getSelfLink(String id);
}