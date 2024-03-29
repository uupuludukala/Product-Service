package com.coolbook.erp.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "company")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class CompanyEntity extends BaseEntity{
	
	
	@Id
	@Column
	@GeneratedValue(generator="company_seq")
	@SequenceGenerator(name="company_seq",sequenceName="company_seq" ,allocationSize=1)
	private long id;
	
	@Column(unique = true)
	private String companyName;
	
	@Column(unique = true)    
	private String companyCode;
	
	@Column
	private String addressLine1;
	
	@Column
	private String addressLine2;
	
	@Column
	private String addressLine3;
	
	@Column
	private String contactNumber;

    @Column
    private String status;

	@Override
	public String toString(){
	    return super.toString();
    }

}
