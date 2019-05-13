package com.coolbook.erp.rest.searchCriteria;

import com.coolbook.erp.entity.ProductCategoryEntity;

import lombok.Data;

@Data
public class ProductCriteria {
	private String id;
	private String productCode;
	private String productName;
	private String productType;
	private String productCategory;
	private String barcode;
	private boolean active=true;
}
