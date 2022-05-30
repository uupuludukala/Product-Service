package com.coolbook.erp.rest.searchCriteria;

import lombok.Data;

import java.util.Date;

@Data
public class VendorPaymentCriteria {

    private String vendorName;
    private Date date;
}
