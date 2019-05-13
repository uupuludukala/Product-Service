package com.coolbook.model;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class CustomerPost {

	
	private String nicNumber;
	private String firstName;
	private String lastName;
	private String addressLine1;
	private String addressLine2;
	private String addressLine3;
	private String mobileNumer;
	private String homePhone;
	@Valid
	@NotNull
	private double creditLimit;
}
