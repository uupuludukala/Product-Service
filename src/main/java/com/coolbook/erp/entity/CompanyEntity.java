package com.coolbook.erp.entity;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Entity
@Table(name = "company")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class CompanyEntity {
	
	
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
	
	@ManyToMany(mappedBy = "companies", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<ProductEntity> products;
	
	@Override
	public String toString(){
	    return super.toString();
    }

}
