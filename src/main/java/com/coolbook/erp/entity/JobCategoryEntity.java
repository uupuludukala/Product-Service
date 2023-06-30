package com.coolbook.erp.entity;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "job_category")
public class JobCategoryEntity {

    @Id
    @Column
    @GeneratedValue(generator = "job_category_seq")
    @SequenceGenerator(name = "job_category_seq", sequenceName = "job_category_seq", allocationSize = 1)
    private long id;

    @Column
    private String categoryName;

    @Column
    private String description;

    public JobCategoryEntity(long id){
        this.id=id;
    }

    public JobCategoryEntity(){

    }
}
