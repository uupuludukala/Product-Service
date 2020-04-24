package com.coolbook.erp.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CompanyPost {
	@JsonProperty("companyName")
	private String companyName;
	
	@JsonProperty("companyCode")
	private String companyCode;
	
	@JsonProperty("addressLine1")
	private String addressLine1;
	
	@JsonProperty("addressLine2")
	private String addressLine2;
	
	@JsonProperty("addressLine3")
	private String addressLine3;
	
	@JsonProperty("contactNumber")
	private String contactNumber;
}
