package com.coolbook.erp.model;

import com.coolbook.erp.common.enums.StatusEnum;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder({"productCatName","productCatCode","parentCategory"})
public class ProductCategoryPost {
	@JsonProperty("productCatCode")
	private String productCatCode;
	
	@JsonProperty("parentCategory")
	private long parentCategory;
	
	@JsonProperty("productCatName")
	private String productCatName;
	
	@JsonProperty("status")
    private StatusEnum status;
	
}
