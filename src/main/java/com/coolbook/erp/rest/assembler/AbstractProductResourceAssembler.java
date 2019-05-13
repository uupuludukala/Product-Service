package com.coolbook.erp.rest.assembler;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.coolbook.erp.entity.ProductEntity;
import com.coolbook.model.ProductGet;

public abstract class AbstractProductResourceAssembler extends ResourceAssemblerSupport<ProductEntity, ProductGet> {
	protected String requestURI;

	public AbstractProductResourceAssembler() {
		super(ProductEntity.class, ProductGet.class);
	}

	@Override
	public ProductGet toResource(ProductEntity productEntity) {
		return createProductJson(productEntity);
	}

	private ProductGet createProductJson(ProductEntity productEntity) {
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

	protected abstract String getSelfLink(String id);
}