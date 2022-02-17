package com.coolbook.erp.model;

import com.coolbook.erp.common.enums.StatusEnum;
import lombok.Data;

@Data
public class EmployeePost {

    private String nicNumber;
    private String employeeName;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String mobileNumer;
    private String homePhone;
    private String imageUrl;
    private StatusEnum status;
}
