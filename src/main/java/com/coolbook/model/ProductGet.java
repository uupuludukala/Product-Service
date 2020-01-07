package com.coolbook.model;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ProductGet extends ResourceSupport {
	@JsonProperty("id")
	private long product_Id;
	
	private boolean canBeSold;
	
	
	private boolean canBePurchased;
	private String internalReference;
	private String productName;
	private String productType;
	private long productCategory;
	private String barcode;
	private String internalNotes;
	private double salePrice;
	private double cost;
	private boolean active;
	private double quantity;
	private String imageUrl;
	private boolean availableInPos;
	private boolean makeToOrder;
	private double customerLeadTime;
	private String descDelOrder;
	private String descReceipt;
	private double weight;
	private double volume;
	private long responsible;
}
