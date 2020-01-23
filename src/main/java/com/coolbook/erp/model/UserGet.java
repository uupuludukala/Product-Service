package com.coolbook.erp.model;

import org.springframework.hateoas.ResourceSupport;

import lombok.Data;

@Data
public class UserGet extends ResourceSupport{
	
	private long user_Id;
	private String userName;
	private  String branch;
}
