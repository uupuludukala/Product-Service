package com.coolbook.erp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "customer")
public class CustomerEntity {
	public CustomerEntity(long id) {
		this.id = id;
	}

	public CustomerEntity() {

	}

	@Id
	@Column
	@GeneratedValue(generator = "customer_seq")
	@SequenceGenerator(name = "customer_seq", sequenceName = "customer_seq", allocationSize = 1)
	private long id;
	@Column(unique = true)
	private String nicNumber;
	
	@Column
	private String customerName;
	
	@Column
	private String addressLine1;
	
	@Column
	private String addressLine2;
	
	@Column
	private String addressLine3;
	
	@Column
	private String mobileNumber;
	
	@Column
	private String homePhone;
	
	@Column
	private double creditLimit;
	
	@Column
	private String imageUrl;

    @Column
    private String status;

    @Column
    private String occupation;

    @Column
    private String company;

}
