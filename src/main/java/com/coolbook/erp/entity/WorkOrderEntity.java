package com.coolbook.erp.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "workOrder")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class WorkOrderEntity {

    @Id
    @Column
    @GeneratedValue(generator = "work_order_seq")
    @SequenceGenerator(name = "work_order_seq", sequenceName = "work_order_seq", allocationSize = 1)
    private long id;

    @Column
    private String workOrderNumber;

    @Column
    private String workOrderDescription;

    @OneToMany(mappedBy = "workOrder", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<WorkOrderComponentEntity> components;

    @OneToMany(mappedBy = "workOrder", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL )
    private List<WorkOrderLabourEntity> labours;

    @OneToOne
    private EmployeeEntity supervisor;

    @OneToOne
    private EmployeeEntity technician;

    @OneToOne
    private EmployeeEntity helper;

    @Column
    private Date startDate;

    @Column
    private Date targetFinishDate;

    @Column
    private Date endDate;

    @Column
    private double totalCost;
}
