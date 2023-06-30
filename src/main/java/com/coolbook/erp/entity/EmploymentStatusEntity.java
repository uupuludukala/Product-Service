package com.coolbook.erp.entity;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "employee_status")
public class EmploymentStatusEntity {

    @Id
    @Column
    @GeneratedValue(generator = "employee_status_seq")
    @SequenceGenerator(name = "employee_status_seq", sequenceName = "employee_status_seq", allocationSize = 1)
    private long id;

    @Column
    private String statusName;

    @Column
    private String description;
}
