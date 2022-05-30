package com.coolbook.erp.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.hateoas.ResourceSupport;

@Data
public class VendorPaymentGet  extends ResourceSupport {

    @JsonProperty("id")
    private long vendor_payment_id;
    private long vendorId;
    private String vendorName;
    private String date;
    private String bankName;
    private String bankBranch;
    private String accountNumber;
    private double amount;
    private String remarks;
}
