package com.coolbook.erp.model;

import java.util.List;

import com.coolbook.erp.common.enums.StatusEnum;
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

	@JsonProperty("barCode")
	private String barCode;

	@JsonProperty("productCode")
	private String productCode;

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
	
	@JsonProperty("companies")
	private List<Long> companies;
	
    @JsonProperty("status")
    private StatusEnum status;

    @JsonProperty("warrantyMonths")
    private int warrantyMonths;

    @JsonProperty("warrantyYears")
    private int warrantyYears;
	
	
}
