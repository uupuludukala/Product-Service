package com.coolbook.erp.model;

import javax.validation.constraints.Pattern;

import com.coolbook.erp.common.enums.StatusEnum;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class BranchPost {
	
	@JsonProperty("branchCode")
	private String branchCode;
	
	@JsonProperty("branchName")
	private String branchName;
	
	@JsonProperty("addressLine1")
	private String addressLine1;

	@JsonProperty("addressLine2")
	private String addressLine2;

	@JsonProperty("addressLine3")
	private String addressLine3;

	@JsonProperty("contactNumber")
	private String contactNumber;
	
	@JsonProperty("email")
	@Pattern(regexp="^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$")
	private String email;
	
	@JsonProperty("companyId")    
	private long companyId;
	
    @JsonProperty("status")
    private StatusEnum status;
	
}
