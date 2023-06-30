package com.coolbook.erp.rest.assembler;

import java.util.ArrayList;
import java.util.List;

import com.coolbook.erp.common.enums.StatusEnum;
import com.coolbook.erp.model.CompanyGet;
import com.coolbook.erp.model.ProductGet;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.coolbook.erp.entity.CompanyEntity;
import com.coolbook.erp.entity.ProductEntity;

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
		productGet.setBarCode(productEntity.getBarcode());
		productGet.setProductCode(productEntity.getProductCode());
		productGet.setCost(productEntity.getCost());
		productGet.setProductCategory(productEntity.getProductCategory().getId());
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
		productGet.setStatus(StatusEnum.getByCode(productEntity.getStatus()));
        productGet.setWarrantyMonths(productEntity.getWarrantyMonths());
        productGet.setWarrantyYears(productEntity.getWarrantyYears());
		return productGet;
	}

	protected abstract String getSelfLink(String id);
}