package com.coolbook.erp.model;

import java.util.Date;
import java.util.List;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;


@Data
@Validated
public class InvoicePost {

    @Valid
	private List<InvoiceProduct> productList;
	private double total;
	private double cashAmount;
	private String paymentMethod;
	private double totalDiscount;
	private long customerId;
	@Valid
	@NotNull
	private Date date;
}
