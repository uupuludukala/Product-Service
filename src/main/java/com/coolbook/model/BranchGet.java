package com.coolbook.model;

import org.springframework.hateoas.ResourceSupport;

import lombok.Data;

@Data
public class BranchGet extends ResourceSupport{

	private long branch_id;
	private String branchCode;
	private String address1;
	private String address2;
	private String address3;
	private String conatactNumber;
	private long companyId;
}
