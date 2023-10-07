package com.coolbook.erp.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "invoice_income")
public class InvoiceIncomeEntity extends BaseEntity{

    @Id
    @Column
    @GeneratedValue(generator = "invoice_income_seq")
    @SequenceGenerator(name = "invoice_income_seq", sequenceName = "invoice_income_seq", allocationSize = 1)
    private long id;

    @OneToOne
    private InvoiceEntity invoice;

    @OneToOne
    private ProductEntity product;

    @Column
    private double cost;

    @Column
    private double salePrice;

    @Column
    private double quantity; 
    
    
}
