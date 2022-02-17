package com.coolbook.erp.model;

import com.coolbook.erp.common.enums.StatusEnum;
import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ProductCategoryGet extends ResourceSupport {
	@JsonProperty("id")
	private long productCategory_id;
	private long parentCategory;
	private String parentCategoryCode;
	private String productCatCode;
	private String productCatName;
    private StatusEnum status;
}