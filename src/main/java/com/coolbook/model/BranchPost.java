package com.coolbook.model;

import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class BranchPost {
	
	@JsonProperty("branchCode")
	private String branchCode;
	
	@JsonProperty("address1")
	private String address1;

	@JsonProperty("address2")
	private String address2;

	@JsonProperty("address3")
	private String address3;

	@JsonProperty("conatactNumber")
	private String conatactNumber;
	
	@JsonProperty("email")
	@Pattern(regexp="^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$")
	private String email;
	
	@JsonProperty("companyId")
	private long companyId;
	
}
