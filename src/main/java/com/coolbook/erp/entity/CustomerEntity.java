package com.coolbook.erp.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "customer")
public class CustomerEntity extends BaseEntity{
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
