package com.coolbook.erp.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

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
	@Column
	private String companyName;
	@Column
	private String companyCode;
	@Column
	private String addressLine1;
	@Column
	private String addressLine2;
	@Column
	private String addressLine3;
	@Column
	private String conatactNumber;
	
	@ManyToMany(mappedBy = "companies", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<ProductEntity> products;
	
	

}
