package com.coolbook.erp.rest.assembler;

import com.coolbook.erp.common.enums.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.coolbook.erp.entity.ProductCategoryEntity;
import com.coolbook.erp.model.ProductCategoryGet;
import com.coolbook.erp.rest.service.ProductCategoryService;



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
		productCategoryGet.setParentCategoryCode(productCategoryEntity.getParentCategory()!=0?productCategoryService.getProductCategoryById(productCategoryEntity.getParentCategory()).getProductCategoryCode():"");
		productCategoryGet.setProductCatCode(productCategoryEntity.getProductCategoryCode());
		productCategoryGet.setProductCatName(productCategoryEntity.getProductCategoryName());
        productCategoryGet.setStatus(StatusEnum.getByCode(productCategoryEntity.getStatus()));
		return productCategoryGet;
	}

	protected abstract String getSelfLink(String id);
}