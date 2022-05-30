package com.coolbook.erp.model;

import com.coolbook.erp.common.enums.StatusEnum;
import org.springframework.hateoas.ResourceSupport;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Column;

@Data
public class CustomerGet extends ResourceSupport {
	@JsonProperty("id")
	private long customer_Id;
	private String nicNumber;
	private String customerName;	
	private String addressLine1;
	private String addressLine2;
	private String addressLine3;
	private String mobileNumer;
	private String homePhone;
	private double creditLimit;
	private String imageUrl;
	private StatusEnum status;
    private String occupation;
    private String company;
}
