package com.coolbook.erp.model;

import com.coolbook.erp.entity.EmployeeEntity;
import com.coolbook.erp.entity.WorkOrderComponentEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.hateoas.ResourceSupport;

import java.util.Date;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include. NON_NULL)
public class WorkOrderGet extends ResourceSupport {

    @JsonProperty("id")
    private long work_order_Id;

    private String workOrderNumber;

    private String workOrderDescription;

    private List<WorkOrderComponentGet> components;

    private List<WorkOrderLabourGet> labours;

    private EmployeeGet supervisor;

    private EmployeeGet technician;

    private EmployeeGet helper;

    private String startDate;

    private String targetFinishDate;

    private String endDate;

    private double totalCost;
}
