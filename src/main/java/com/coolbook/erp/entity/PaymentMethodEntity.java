


package com.coolbook.erp.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "payment_method")
public class PaymentMethodEntity extends BaseEntity{
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
