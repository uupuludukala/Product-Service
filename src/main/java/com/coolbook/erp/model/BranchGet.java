package com.coolbook.erp.model;

import com.coolbook.erp.common.enums.StatusEnum;
import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class BranchGet extends ResourceSupport{

	@JsonProperty("id")
	private long branch_id;
	private String branchCode;
	private String  branchName;
	private String addressLine1;
	private String addressLine2;
	private String addressLine3;
	private String contactNumber;
	private long companyId;
	private String companyCode;
	private String companyName;
	private StatusEnum status;
}
