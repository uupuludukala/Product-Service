package com.coolbook.erp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.coolbook.erp.common.enums.StatusEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiParam;
import lombok.Data;

@Data
@Entity
@Table(name="oauth_user") 
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UserEntity {
	@Id
	@Column
	@GeneratedValue(generator="user_seq")
	@SequenceGenerator(name="user_seq",sequenceName="user_seq" ,allocationSize=1)
	@ApiParam(hidden = true)
	private long id;
	
	@Column(unique = true)
	private String userName;
	
    @Column
	private String status;
    
	@Column
	private String password;
	
	@OneToOne
	private  BranchEntity branch;
	
}
