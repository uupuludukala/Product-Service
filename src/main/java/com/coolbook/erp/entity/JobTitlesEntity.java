package com.coolbook.erp.entity;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "job_title")
public class JobTitlesEntity {

    @Id
    @Column
    @GeneratedValue(generator = "job_title_seq")
    @SequenceGenerator(name = "job_title_seq", sequenceName = "job_title_seq", allocationSize = 1)
    private long id;


    @Column
    private String jobTitle;

    @Column
    private String jobDescription;

    @Column
    private String note;

    public JobTitlesEntity(long id){
        this.id=id;
    }

    public JobTitlesEntity(){

    }

}
