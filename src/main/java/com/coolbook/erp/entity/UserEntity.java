package com.coolbook.erp.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiParam;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="oauth_user") 
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UserEntity extends BaseEntity{

    public UserEntity(long id) {
        this.id = id;
    }

    public UserEntity() {
        
    }
    
    
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
