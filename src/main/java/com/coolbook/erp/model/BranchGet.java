package com.coolbook.erp.model;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class BranchGet extends ResourceSupport{

	@JsonProperty("id")
	private long branch_id;
	private String branchCode;
	private String address1;
	private String address2;
	private String address3;
	private String conatactNumber;
	private long companyId;
}
