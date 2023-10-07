package com.coolbook.erp.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "invoice_inventory_details")
public class InvoiceInventoryDetailsEntity extends BaseEntity{

    @Id
    @Column
    @GeneratedValue(generator = "invoice_inventory_details_seq")
    @SequenceGenerator(name = "invoice_inventory_details_seq", sequenceName = "invoice_inventory_details_seq", allocationSize = 1)
    private long id;

    @OneToOne
    private ProductInventoryDetailsEntity productInventoryDetails;

    @Column
    private double quantity;
    
    @Column
    private long invoiceId;

}
