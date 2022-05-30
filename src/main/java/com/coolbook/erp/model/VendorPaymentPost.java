package com.coolbook.erp.model;

import com.coolbook.erp.common.enums.PaymentStatusEnum;
import lombok.Data;

import java.util.Date;

@Data
public class VendorPaymentPost {
    
    private long vendorId;
    private Date date;
    private String bankName;
    private String bankBranch;
    private String accountNumber;
    private double amount;
    private String remarks;
    private PaymentStatusEnum status;
}
