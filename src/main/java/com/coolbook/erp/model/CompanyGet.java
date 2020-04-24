package com.coolbook.erp.model;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CompanyGet extends ResourceSupport {
	@JsonProperty("id")
	private long company_id;
	private String companyName;
	private String companyCode;
	private String addressLine1;
	private String addressLine2;
	private String addressLine3;
	private String contactNumber;

}
