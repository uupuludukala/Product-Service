package com.coolbook.erp.entity;

import javax.persistence.*;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "invoice_product")
public class InvoiceProductEntity {
	@Id
	@Column
	@GeneratedValue(generator = "invoice_product_seq")
	@SequenceGenerator(name = "invoice_product_seq", sequenceName = "invoice_product_seq", allocationSize = 1)
	private long id;
	
	@OneToOne
	private ProductEntity product;

    @Column
	private String itemNo;

    @Column
	private String description;

    @Column
	private double amount;

    @Column
    private double cost;

	@Column
	private double unitPrice;
	
	@Column
	private double discount;
	
	@Column
	private double quantity;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "invoice_id", nullable = false)
    private InvoiceEntity invoice;
    
	
}
