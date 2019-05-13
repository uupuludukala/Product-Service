package com.coolbook.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ProductCategoryPost {
	@JsonProperty("productCatCode")
	private String productCatCode;
	@JsonProperty("parentCategory")
	private long parentCategory;
	@JsonProperty("productCatName")
	private String productCatName;
	
	
}
