package com.coolbook.erp.model;

import java.util.List;

import com.coolbook.erp.common.enums.StatusEnum;
import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductGet extends ResourceSupport {
	@JsonProperty("id")
	private long product_Id;
	
	private boolean canBeSold;
	
	
	private boolean canBePurchased;
	private String productName;
	private String productType;
	private long productCategory;
	private String barCode;
	private String productCode;
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
	private List<CompanyGet> companies;
    private StatusEnum status;
}
