package com.coolbook.erp.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;



@Data
@AllArgsConstructor
public class VendorAccountGet {

    @JsonProperty("id")
    private long vendor_account_Id;
    private String accountNumber;
    private String bankName;
    private String branchName;
    
}
