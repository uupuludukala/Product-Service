package com.coolbook.erp.model;

import lombok.Data;


@Data
public class InvoiceProduct {

	private long productId;
	private double quantity;
	private double unitPrice;
	private double discount;
	private double amount;
	private String description;
	
}
