package com.coolbook.erp.model;

import com.coolbook.erp.common.enums.EmployeePaymentFrequencyEnum;
import com.coolbook.erp.common.enums.EmploymentStatusEnum;
import com.coolbook.erp.rest.validator.EmployeePaymentFrequencyConstraint;
import com.coolbook.erp.rest.validator.EmploymentStatusConstraint;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class EmployeeJobPost {

    @Min(value=1,message = "Job title is required")
    private long jobTitleId;

    @NotNull
    private Date joinedDate;

    @Min(value=1,message = "Branch is required")
    private long branchId;

   @EmployeePaymentFrequencyConstraint(enumClass = EmployeePaymentFrequencyEnum.class)
    private EmployeePaymentFrequencyEnum paymentFrequency;


    private double paymentRate;

    @Min(value=1,message = "Job category is required")
    private long jobCategoryId;

    @EmploymentStatusConstraint(enumClass = EmploymentStatusEnum.class)
    private EmploymentStatusEnum employmentStatus;

    @Min(value=1,message = "Work shift is required")
    private long workShiftId;

    @NotNull
    private Date probationEndDate;

}
