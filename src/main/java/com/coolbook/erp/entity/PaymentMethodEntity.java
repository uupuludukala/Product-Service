


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
@Table(name = "payment_method")
public class PaymentMethodEntity {
	public PaymentMethodEntity (long id) {
		this.id=id;
	}
	
	public PaymentMethodEntity() {
		
	}
	@Id
	@Column
	@GeneratedValue(generator = "paymentMethod_seq")
	@SequenceGenerator(name = "paymentMethod_seq", sequenceName = "paymentMethod_seq", allocationSize = 1)
	private long id;
	
	@Column
	private String paymentMethodName;
	
	@Column
	private boolean isCash;
	

}
