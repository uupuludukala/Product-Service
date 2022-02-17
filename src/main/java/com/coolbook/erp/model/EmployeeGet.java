package com.coolbook.erp.model;

import com.coolbook.erp.common.enums.StatusEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.hateoas.ResourceSupport;

@Data
public class EmployeeGet extends ResourceSupport {
    @JsonProperty("id")
    private long employee_Id;
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
