package com.coolbook.erp.security;

import java.io.Serializable;

import lombok.Getter;

@Getter
public class User  implements Serializable{

	private static final long serialVersionUID = -2110734144884441488L;
	
	private String companyCode;
	private String branchCode;
	private long companyId;

	public User(String companyCode, String branchCode, long companyId) {
		this.companyCode = companyCode;
		this.branchCode = branchCode;
		this.companyId = companyId;
	}

}
