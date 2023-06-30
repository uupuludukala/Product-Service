package com.coolbook.erp.model;

import com.coolbook.erp.common.enums.EmployeePaymentFrequencyEnum;
import com.coolbook.erp.common.enums.EmploymentStatusEnum;
import com.coolbook.erp.common.enums.StatusEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include. NON_NULL)
public class EmployeeJobGet {

    @JsonProperty("id")
    private long job_title_Id;
    private JobTitlesGet jobTitle;
    private String joinedDate;
    private BranchGet branch;
    private EmployeePaymentFrequencyEnum paymentFrequency;
    private double paymentRate;
    private JobCategoryGet jobCategory;
    private EmploymentStatusEnum employmentStatus;
    private WorkShiftGet workShift;
    private String probationEndDate;
    private StatusEnum status;
}
