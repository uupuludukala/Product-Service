package com.coolbook.model;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ProductGet extends ResourceSupport {
	@JsonProperty("id")
	private long product_Id;
	private String productCode;
	private String productName;
	private String productType;
	private long productCategory;
	private String barcode;
	private double salePrice;
	private double cost;
	private boolean active;
	private int quantity;
	private String imageUrl;
}
