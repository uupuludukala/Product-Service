package com.coolbook.erp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiParam;
import lombok.Data;

@Data
@Entity
@Table(name = "branch")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class BranchEntity {
	@Id
	@Column
	@GeneratedValue(generator="branch_seq")
	@SequenceGenerator(name="branch_seq",sequenceName="branch_seq" ,allocationSize=1)
	private long id;
	@Column
	private String branchCode;
	@Column
	private String address1;
	@Column
	private String address2;
	@Column
	private String address3;
	@Column
	private String conatactNumber;
	@OneToOne
	@ApiParam(hidden=true)
	private CompanyEntity company;
	
	
	

}
