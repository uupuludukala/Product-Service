package com.coolbook.erp.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "product_inventory_details")
public class ProductInventoryDetailsEntity {
    @Id
    @Column
    @GeneratedValue(generator = "product_inventory_details_seq")
    @SequenceGenerator(name = "product_inventory_details_seq", sequenceName = "product_inventory_details_seq", allocationSize = 1)
    private long id;

    @OneToOne
    private ProductEntity product;

    @OneToOne
    private PurchaseOrderEntity purchaseOrder;
    
    @Column
    private Date date;

    @Column
    private double rate;

    @Column
    private double cost;

    @Column
    private double quantity;
    
    
}
