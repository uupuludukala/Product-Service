
package com.coolbook.erp.model;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class PaymentMethodGet extends ResourceSupport {
	@JsonProperty("paymentMethod_id")
	private long paymentMethod_id;
	private String paymentMethodName;
	private boolean isCash;

}
