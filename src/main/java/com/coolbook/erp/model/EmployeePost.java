package com.coolbook.erp.model;

import com.coolbook.erp.common.enums.GenderEnum;
import com.coolbook.erp.common.enums.MaritalStatusEnum;
import com.coolbook.erp.common.enums.StatusEnum;
import com.coolbook.erp.rest.validator.GenderConstraint;
import com.coolbook.erp.rest.validator.MaritalStatusConstraint;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
public class EmployeePost {

    @NotNull
    @NotEmpty
    private String nicNumber;

    @NotNull
    @NotEmpty
    private String employeeName;

    @NotNull
    @NotEmpty
    private String addressLine1;

    @NotNull
    @NotEmpty
    private String addressLine2;

    private String addressLine3;

    @NotNull
    @NotEmpty
    private String mobileNumber;

    private String homePhone;

    private String imageUrl;

    private StatusEnum status;

    @NotNull
    @NotEmpty
    private String bankName;

    @NotNull
    @NotEmpty
    private String bankBranch;

    @NotNull
    @NotEmpty
    private String accountNumber;

    private String employeeId;

    @MaritalStatusConstraint(enumClass = MaritalStatusEnum.class)
    private MaritalStatusEnum maritalStatus;

    @GenderConstraint(enumClass = GenderEnum.class)
    private GenderEnum gender;

    @Valid
    private EmployeeJobPost employeeJob;
}
