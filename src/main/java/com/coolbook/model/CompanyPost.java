package com.coolbook.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CompanyPost {
	@JsonProperty("companyName")
	private String companyName;
	
	@JsonProperty("companyCode")
	private String companyCode;
	
	@JsonProperty("address1")
	private String address1;
	
	@JsonProperty("address2")
	private String address2;
	
	@JsonProperty("address3")
	private String address3;
	
	@JsonProperty("contactNumber")
	private String contactNumber;
}
