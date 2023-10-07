package com.coolbook.erp.entity;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "work_shift")
public class WorkShiftEntity extends BaseEntity{

    @Id
    @Column
    @GeneratedValue(generator = "work_shift_seq")
    @SequenceGenerator(name = "work_shift_seq", sequenceName = "work_shift_seq", allocationSize = 1)
    private long id;

    @Column
    private String workShiftName;

    @Column
    private String timeFrom;

    @Column
    private String timeTo;

    @Column
    private double hoursPerDay;

    public WorkShiftEntity(long id){
        this.id=id;
    }

    public WorkShiftEntity(){
        
    }
}
