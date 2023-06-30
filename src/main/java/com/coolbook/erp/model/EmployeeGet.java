package com.coolbook.erp.model;

import com.coolbook.erp.common.enums.StatusEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.hateoas.ResourceSupport;

@Data
@JsonInclude(JsonInclude.Include. NON_NULL)
public class EmployeeGet extends ResourceSupport {

    @JsonProperty("id")
    private long employee_Id;
    private String nicNumber;
    private String employeeName;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String mobileNumber;
    private String homePhone;
    private String imageUrl;
    private StatusEnum status;
    private String bankName;
    private String bankBranch;
    private String accountNumber;

    private EmployeeJobGet employeeJob;


    public EmployeeGet(long id,String employeeName){
        this.employee_Id=id;
        this.employeeName=employeeName;
    }

    public EmployeeGet(){

    }
}
