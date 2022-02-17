package com.coolbook.erp.model;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.coolbook.erp.common.enums.StatusEnum;
import lombok.Data;

@Data
public class CustomerPost {

	
	private String nicNumber;
	private String customerName;
	private String addressLine1;
	private String addressLine2;
	private String addressLine3;
	private String mobileNumer;
	private String homePhone;
	@Valid
	@NotNull
	private double creditLimit;
	private String imageUrl;
	private StatusEnum status;
}
