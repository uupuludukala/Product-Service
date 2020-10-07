package com.coolbook.erp.model;

import com.coolbook.erp.common.enums.StatusEnum;
import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CompanyGet extends ResourceSupport {
    public CompanyGet(){
        
    }
    public CompanyGet(long  company_id,String companyCode,String companyName){
        this.company_id=company_id;
        this.companyCode=companyCode;
        this.companyName=companyName;
    }
	@JsonProperty("id")
	private long company_id;
	private String companyName;
	private String companyCode;
	private String addressLine1;
	private String addressLine2;
	private String addressLine3;
	private String contactNumber;
    private StatusEnum status;

}
