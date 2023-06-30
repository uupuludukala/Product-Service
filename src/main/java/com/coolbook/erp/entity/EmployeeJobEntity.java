package com.coolbook.erp.entity;

import com.coolbook.erp.common.enums.EmployeePaymentFrequencyEnum;
import com.coolbook.erp.common.enums.EmploymentStatusEnum;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;


@Data
@Entity
@Table(name = "employee_job")
public class EmployeeJobEntity {

    @Id
    @Column
    @GeneratedValue(generator = "employee_job_seq")
    @SequenceGenerator(name = "employee_job_seq", sequenceName = "employee_job_seq", allocationSize = 1)
    private long id;

    @OneToOne
    private JobTitlesEntity jobTitle;

    @Column
    private Date joinedDate;

    @Column
    private long branchId;

    @Column
    private EmployeePaymentFrequencyEnum paymentFrequency;

    @Column
    private double paymentRate;

    @OneToOne
    private JobCategoryEntity jobCategory;

    @Column
    private EmploymentStatusEnum employmentStatus;

    @OneToOne
    private WorkShiftEntity workShift;

    @Column
    private Date probationEndDate;

    @OneToOne(mappedBy = "employeeJob")
    private EmployeeEntity employee;

}
