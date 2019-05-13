package com.coolbook.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ProductPost {
	@JsonProperty("productCode")
	private String productCode;
	
	@JsonProperty("productName")
	private String productName;
	
	@JsonProperty("productType")
	private String productType;
	
	@JsonProperty("productCategory")
	private long productCategory;
	
	@JsonProperty("barcode")
	private String barcode;
	
	@JsonProperty("salePrice")
	private double salePrice;
	
	@JsonProperty("cost")
	private double cost;
	
	@JsonProperty("active")
	private boolean active;
	
	@JsonProperty("quantity")
	private int quantity;
	
	@JsonProperty("imageUrl")
	private String imageUrl;
}
