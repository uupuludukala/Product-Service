package com.coolbook.model;

import java.util.List;

import lombok.Data;

@Data
public class InvoicePost {

	private List<InvoiceProduct> productList;
	private double total;
	private double cashAmount;
	private String paymentMethod;
	private double totalDiscount;
	private long customerId;
}
