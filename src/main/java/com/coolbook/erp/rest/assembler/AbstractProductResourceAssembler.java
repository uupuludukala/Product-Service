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
		productGet.setCanBeSold(productEntity.isCanBeSold());
		productGet.setCanBePurchased(productEntity.isCanBePurchased());
		productGet.setActive(productEntity.isActive());
		productGet.setBarcode(productEntity.getBarcode());
		productGet.setInternalNotes(productEntity.getInternalNotes());
		productGet.setCost(productEntity.getCost());
		productGet.setProductCategory(productEntity.getProductCategory().getId());
		productGet.setInternalReference(productEntity.getInternalReference());
		productGet.setProductName(productEntity.getProductName());
		productGet.setProductType(productEntity.getProductType());
		productGet.setQuantity(productEntity.getQuantity());
		productGet.setSalePrice(productEntity.getSalePrice());
		productGet.setImageUrl(productEntity.getImageUrl());
		productGet.setAvailableInPos(productEntity.isAvailableInPos());
		productGet.setMakeToOrder(productEntity.isMakeToOrder());
		productGet.setCustomerLeadTime(productEntity.getCustomerLeadTime());
		productGet.setDescDelOrder(productEntity.getDescDelOrder());
		productGet.setDescReceipt(productEntity.getDescReceipt());
		productGet.setWeight(productEntity.getWeight());
		productGet.setVolume(productEntity.getVolume());
		productGet.setResponsible(productEntity.getResponsible());

		return productGet;
	}

	protected abstract String getSelfLink(String id);
}