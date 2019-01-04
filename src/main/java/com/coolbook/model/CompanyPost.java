package com.coolbook.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CompanyPost {
	@JsonProperty("companyName")
	private String companyName;
	
	@JsonProperty("companyCode")
	private String companyCode;
	
	@JsonProperty("adress1")
	private String adress1;
	
	@JsonProperty("adress2")
	private String adress2;
	
	@JsonProperty("adress3")
	private String adress3;
	
	@JsonProperty("conatactNumber")
	private String conatactNumber;
}
