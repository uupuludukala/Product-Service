package com.coolbook.erp.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "workOrderLabour")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class WorkOrderLabourEntity extends BaseEntity{

    @Id
    @Column
    @GeneratedValue(generator = "work_order_labour_seq")
    @SequenceGenerator(name = "work_order_labour_seq", sequenceName = "work_order_labour_seq", allocationSize = 1)
    private long id;

    @OneToOne
    private EmployeeEntity employee;

    private double hours;
    private double rate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "work_order_id", nullable = false)
    private WorkOrderEntity workOrder;
}
