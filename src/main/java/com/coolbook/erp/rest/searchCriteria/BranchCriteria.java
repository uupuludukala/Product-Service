package com.coolbook.erp.rest.searchCriteria;

import lombok.Data;

@Data
public class BranchCriteria {
	private String id;
	private String branchCode;
	private String branchName;
	private String address;
	private String contactNumber;
}
