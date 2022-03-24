package com.coolbook.erp.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ProductInventoryDetailsGet {

    private long id;    
    private String productName;
    private String productCode;
    private String purchaseOrderNumber;
    private String date;
    private double rate;
    private double cost;
    private double quantity;
}
