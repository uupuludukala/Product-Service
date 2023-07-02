package com.coolbook.erp.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "petty_cash")
public class PettyCashEntity {

    @Id
    @Column
    @GeneratedValue(generator = "petty_cash_seq")
    @SequenceGenerator(name = "petty_cash_seq", sequenceName = "petty_cash_seq", allocationSize = 1)
    private long id;

    @Column
    private Date date;

    @Column
    private String description;

    @Column
    private double amount;

}
