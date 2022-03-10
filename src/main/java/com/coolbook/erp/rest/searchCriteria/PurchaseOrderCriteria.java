package com.coolbook.erp.rest.searchCriteria;

import lombok.Data;

import java.util.Date;

@Data
public class PurchaseOrderCriteria {

    private String purchaseOrderNumber;
    private Date date;
}
