package com.coolbook.erp.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class WorkOrderPost {
   
    private String workOrderNumber;
   
    private String workOrderDescription;

    private List<WorkOrderComponent> components;

    private List<WorkOrderLabour> labours;

    private long supervisor;

    private long technician;

    private long helper;
   
    private Date startDate;
   
    private Date targetFinishDate;
   
    private Date endDate;
}
