package com.coolbook.erp.model;

import com.coolbook.erp.common.enums.VendorTypeEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;

@Data
public class VendorGet extends ResourceSupport {
    @JsonProperty("id")
    private long vendor_Id;
    private VendorTypeEnum vendorType;
    private String vendorName;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String contactPerson;
    private String phone;
    private String mobile;
    private String email;
    private List<VendorAccountGet> vendorAccounts;
    
    public VendorGet(long vendor_Id,String vendorName){
        this.vendor_Id=vendor_Id;
        this.vendorName=vendorName;
    }

    public VendorGet(){
        
    }
    
}
