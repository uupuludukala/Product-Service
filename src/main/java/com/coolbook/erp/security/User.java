package com.coolbook.erp.security;

import java.io.Serializable;

public class User  implements Serializable {
	
	private static final long serialVersionUID = -2110734144884441488L;
	
	private final String userId;
	private final String userFullName;
	private final String username;
	private final String customer;

	public User(String userId, String userFullName, String username, String customer) {
		this.userId = userId;
		this.userFullName = userFullName;
		this.username = username;
		this.customer = customer;
	}

	public String getUserId() {
		return userId;
	}

	public String getUsername() {
		return username;
	}

	public String getCustomer() {
		return customer;
	}

	public String getUserFullName() {
		return this.userFullName;
	}
}
