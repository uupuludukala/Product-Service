package com.coolbook.erp.entity;

import javax.persistence.*;

import lombok.Data;

@Data
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
	private double unitPrice;
	
	@Column
	private double discount;
	
	@Column
	private double quantity;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "invoice_id", nullable = false)
    private InvoiceEntity invoice;
    
	
}
