package com.coolbook.erp.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "employee")
public class EmployeeEntity {

    public EmployeeEntity(long id) {
        this.id = id;
    }

    public EmployeeEntity() {

    }

    @Id
    @Column
    @GeneratedValue(generator = "employee_seq")
    @SequenceGenerator(name = "employee_seq", sequenceName = "employee_seq", allocationSize = 1)
    private long id;

    @Column(unique = true)
    private String nicNumber;

    @Column
    private String employeeName;

    @Column
    private String addressLine1;

    @Column
    private String addressLine2;

    @Column
    private String addressLine3;

    @Column
    private String mobileNumber;

    @Column
    private String homePhone;

    @Column
    private String imageUrl;
    
    @Column
    private String status;
}
