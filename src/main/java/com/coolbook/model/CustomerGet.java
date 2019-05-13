package com.coolbook.model;

import org.springframework.hateoas.ResourceSupport;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CustomerGet extends ResourceSupport {
	@JsonProperty("id")
	private long customer_Id;
	private String nicNumber;
	private String firstName;
	private String lastName;
	private String addressLine1;
	private String addressLine2;
	private String addressLine3;
	private String mobileNumer;
	private String homePhone;
	private double creditLimit;
}
