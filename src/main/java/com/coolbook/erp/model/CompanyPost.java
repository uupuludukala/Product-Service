package com.coolbook.erp.model;

import com.coolbook.erp.common.enums.StatusEnum;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

@Data
public class CompanyPost {
	@JsonProperty("companyName")
    @NotNull
    @NotEmpty
	private String companyName;
	
	@JsonProperty("companyCode")
    @NotNull
    @NotEmpty
	private String companyCode;
	
	@JsonProperty("addressLine1")
    @NotNull
    @NotEmpty
	private String addressLine1;
	
	@JsonProperty("addressLine2")
    @NotNull
    @NotEmpty
	private String addressLine2;
	
	@JsonProperty("addressLine3")
	private String addressLine3;
	
	@JsonProperty("contactNumber")
    @NotNull
    @NotEmpty
	private String contactNumber;
	
    @JsonProperty("status")
    private StatusEnum status;
}
