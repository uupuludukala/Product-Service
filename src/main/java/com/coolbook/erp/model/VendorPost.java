package com.coolbook.erp.model;

import com.coolbook.erp.common.enums.VendorTypeEnum;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
public class VendorPost {
   
    private VendorTypeEnum vendorType;
    private String vendorName;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String contactPerson;
    private String phone;
    private String mobile;
    private String email;
    private Set<VendorAccount> vendorAccounts;
}
