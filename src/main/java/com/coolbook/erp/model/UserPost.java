package com.coolbook.erp.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class UserPost {

	@JsonProperty("userName")
	private String userName;
	@JsonProperty("pasword")
	private String pasword;
	@JsonProperty("branch")
	private  long branch;
}
