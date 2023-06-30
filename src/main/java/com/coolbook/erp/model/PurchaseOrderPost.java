package com.coolbook.erp.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class PurchaseOrderPost {

    private String purchaseOrderNumber;
    private Date date; 
    private List<PurchaseOrderProduct> purchaseOrderProducts;
    private double total;
    private double totalDiscount;
    private long vendorId;
    
}
