package com.coolbook.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ProductPost {

	@JsonProperty("canBeSold")
	private boolean canBeSold;
	
	@JsonProperty("canBePurchased")
	private boolean canBePurchased;

	@JsonProperty("productName")
	private String productName;

	@JsonProperty("productType")
	private String productType;

	@JsonProperty("productCategory")
	private long productCategory;

	@JsonProperty("internalReference")
	private String internalReference;

	@JsonProperty("barcode")
	private String barcode;

	@JsonProperty("internalNotes")
	private String internalNotes;

	@JsonProperty("salePrice")
	private double salePrice;

	@JsonProperty("cost")
	private double cost;

	@JsonProperty("active")
	private boolean active;

	@JsonProperty("quantity")
	private double quantity;

	@JsonProperty("imageUrl")
	private String imageUrl;
	
	@JsonProperty("availableInPos")
	private boolean availableInPos;
	
	@JsonProperty("makeToOrder")
	private boolean makeToOrder;
	
	@JsonProperty("customerLeadTime")
	private double customerLeadTime;
	
	@JsonProperty("descDelOrder")
	private String descDelOrder;
	
	@JsonProperty("descReceipt")
	private String descReceipt;
	
	@JsonProperty("weight")
	private double weight;
	
	@JsonProperty("volume")
	private double volume;
	
	@JsonProperty("responsible")
	private long responsible;
	
	
	
}
