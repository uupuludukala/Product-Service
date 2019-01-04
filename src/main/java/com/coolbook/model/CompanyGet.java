package com.coolbook.model;

import org.springframework.hateoas.ResourceSupport;

import lombok.Data;

@Data
public class CompanyGet extends ResourceSupport {
	private long company_id;
	private String companyName;
	private String companyCode;
	private String adress1;
	private String adress2;
	private String adress3;
	private String conatactNumber;

}
