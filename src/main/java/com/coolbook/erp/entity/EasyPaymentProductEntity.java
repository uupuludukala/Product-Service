package com.coolbook.erp.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "easyPayment_product")
public class EasyPaymentProductEntity extends BaseEntity{
    @Id
    @Column
    @GeneratedValue(generator = "easyPayment_product_seq")
    @SequenceGenerator(name = "easyPayment_product_seq", sequenceName = "easyPayment_product_seq", allocationSize = 1)
    private long id;

    @OneToOne
    private ProductEntity product;

    @Column
    private String itemNo;

    @Column
    private String description;

    @Column
    private double quantity;

    @Column
    private double rate;

    @Column
    private double amount;

    @Column
    private double total;

    @Column
    private double cost;

    @Column
    private double discount;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "easy_payment_id", nullable = false)
    private EasyPaymentEntity easyPayment;


}
