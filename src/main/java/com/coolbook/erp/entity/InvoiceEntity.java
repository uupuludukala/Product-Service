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
@Table(name = "invoice")
public class InvoiceEntity {
	@Id
	@Column
	@GeneratedValue(generator = "invoice_seq")
	@SequenceGenerator(name = "invoice_seq", sequenceName = "invoice_seq", allocationSize = 1)
	private long id;
	
    @Column
	private String invoiceNumber;
    
	@OneToOne
	private UserEntity user;
	
	@Column
	private Date date;

  
    @OneToMany(mappedBy = "invoice", fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
	private Set<InvoiceProductEntity> products;
	
	@Column
	private double total;
	
	@Column
	private double cashAmount;
	
	@Column
	private String paymentMethod;
	
	@Column
	private double totalDiscount;
	
	@OneToOne
	private CustomerEntity customer;

    @OneToOne
    private BranchEntity branch;


}
