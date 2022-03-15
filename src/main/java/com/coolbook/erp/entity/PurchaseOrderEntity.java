package com.coolbook.erp.entity;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "purchase_order")
public class PurchaseOrderEntity {
    @Id
    @Column
    @GeneratedValue(generator = "purchase_order_seq")
    @SequenceGenerator(name = "purchase_order_seq", sequenceName = "purchase_order_seq", allocationSize = 1)
    private long id;

    @Column
    private String purchaseOrderNumber;
    
    @Column
    private Date date;

     @OneToMany(mappedBy = "purchaseOrder",
            cascade = CascadeType.ALL , orphanRemoval=true)
    private List<PurchaseOrderProductEntity> purchaseOrderProducts;

    @Column
    private double total;

    @OneToOne
    private VendorEntity vendor;
    
    @Column
    private boolean approved=false;

}
