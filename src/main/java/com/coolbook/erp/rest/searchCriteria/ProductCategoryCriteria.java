package com.coolbook.erp.rest.searchCriteria;

import lombok.Data;
@Data
public class ProductCategoryCriteria {
	
	private String id;
	private String parentCategory;
	private String productCatCode;
	private String productCatName;
}
