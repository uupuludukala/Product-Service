package com.coolbook.erp.model;

import lombok.Data;

@Data
public class PurchaseOrderProduct {

    private long productId;
    private double rate;
    private double discount;    
    private double quantity;
    private double amount;
}
