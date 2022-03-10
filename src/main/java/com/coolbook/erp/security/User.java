package com.coolbook.erp.security;

import java.io.Serializable;

import lombok.Getter;

@Getter
public class User  implements Serializable{

	private static final long serialVersionUID = -2110734144884441488L;
	
	private String companyCode;
	private String branchCode;
    private long branchId;
	private long companyId;
	private long userId;

	public User(String companyCode, String branchCode, long companyId,long userId,long branchId) {
		this.companyCode = companyCode;
		this.branchCode = branchCode;
		this.companyId = companyId;
		this.userId=userId;
		this.branchId=branchId;
	}

}
