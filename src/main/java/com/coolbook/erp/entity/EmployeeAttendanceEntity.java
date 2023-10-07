package com.coolbook.erp.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "employee_attendance")
public class EmployeeAttendanceEntity extends BaseEntity{

    @Id
    @Column
    @GeneratedValue(generator = "employee_attendance_seq")
    @SequenceGenerator(name = "employee_attendance_seq", sequenceName = "employee_attendance_seq", allocationSize = 1)
    private long id;

    @ManyToOne
    private EmployeeEntity employee;

    @Column
    private LocalDateTime timeIn;

    @Column
    private LocalDateTime timeOut;

}
