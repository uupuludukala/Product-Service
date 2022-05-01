package com.coolbook.erp.model;

import lombok.Data;


@Data
public class InvoiceProduct {

	private long productId;
	private double quantity;
	private double rate;
	private double discount;
	private double amount;
    private double total;
	private String description;
	
}
