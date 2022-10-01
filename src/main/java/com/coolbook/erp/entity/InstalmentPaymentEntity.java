package com.coolbook.erp.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "instalment_payment")
public class InstalmentPaymentEntity {

    @Id
    @Column
    @GeneratedValue(generator = "instalment_payment_seq")
    @SequenceGenerator(name = "instalment_payment_seq", sequenceName = "instalment_payment_seq", allocationSize = 1)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "easy_payment_id", nullable = false)
    private EasyPaymentEntity easyPayment;

    @Column
    private Date date;

    @Column
    private double amount;


}
