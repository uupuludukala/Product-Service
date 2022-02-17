package com.coolbook.erp.model;

import com.coolbook.erp.common.enums.StatusEnum;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class UserPost {

	@JsonProperty("userName")
	private String userName;
	
	@JsonProperty("password")
	private String password;
	
	@JsonProperty("branch")
	private  long branch;
	
    @JsonProperty("status")
	private StatusEnum status;
}
