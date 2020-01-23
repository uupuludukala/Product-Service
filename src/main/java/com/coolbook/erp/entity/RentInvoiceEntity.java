package com.coolbook.erp.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "invoice")
public class RentInvoiceEntity {
	@Id
	@Column
	@GeneratedValue(generator = "rent_invoice_seq")
	@SequenceGenerator(name = "rent_invoice_seq", sequenceName = "rent_invoice_seq", allocationSize = 1)
	private long id;
	@OneToOne
	private UserEntity user;
	@Column
	private Date date;
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "id")
	private List<InvoiceProductEntity> products;
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
}
