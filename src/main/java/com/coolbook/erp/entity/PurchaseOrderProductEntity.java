package com.coolbook.erp.entity;

import javax.persistence.*;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "purchase_order_product")
public class PurchaseOrderProductEntity {
    @Id
    @Column
    @GeneratedValue(generator = "purchase_order_product_seq")
    @SequenceGenerator(name = "purchase_order_product_seq", sequenceName = "purchase_order_product_seq", allocationSize = 1)
    private long id;

    @OneToOne
    private ProductEntity product;

    @Column
    private double rate;

    @Column
    private double discount;

    @Column
    private double quantity;

    @Column
    private double amount;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "purchase_order_id", nullable = false)
    private PurchaseOrderEntity purchaseOrder;


}
