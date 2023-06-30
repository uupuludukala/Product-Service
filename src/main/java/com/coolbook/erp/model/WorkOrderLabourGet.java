package com.coolbook.erp.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include. NON_NULL)
public class WorkOrderLabourGet {

    private EmployeeGet employee;
    private double hours;
    private double rate;

}
