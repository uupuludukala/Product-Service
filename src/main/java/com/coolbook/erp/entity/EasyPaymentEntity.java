package com.coolbook.erp.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "easy_payment")
public class EasyPaymentEntity extends BaseEntity{

    @Id
    @Column
    @GeneratedValue(generator = "easy_payment_seq")
    @SequenceGenerator(name = "easy_payment_seq", sequenceName = "easy_payment_seq", allocationSize = 1)
    private long id;

    @Column
    private String invoiceNumber;

    @OneToOne
    private CustomerEntity customer;

    @OneToMany(cascade= {CascadeType.ALL},fetch=FetchType.LAZY)
    private List<CustomerEntity> guarantors;

    @Column
    private Date date;

    @OneToMany(mappedBy = "easyPayment", fetch = FetchType.EAGER,
            cascade = CascadeType.ALL , orphanRemoval=true)
    private Set<EasyPaymentProductEntity> products;

    @Column
    private double downPayment;

    @Column
    private int instalments;

    @Column
    private double instalmentAmount;

    @Column
    private boolean paymentCompleted;

    @OneToMany(mappedBy = "easyPayment", fetch = FetchType.EAGER,
            cascade = CascadeType.ALL , orphanRemoval=true)
    private List<InstalmentPaymentEntity> instalmentPayments;

}
