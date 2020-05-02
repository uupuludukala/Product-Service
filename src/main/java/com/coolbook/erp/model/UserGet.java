package com.coolbook.erp.model;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class UserGet extends ResourceSupport{
	@JsonProperty("id")
	private long user_Id;
	private String userName;
	private  String branch;
	private long companyId;
	private long branchId;
}
