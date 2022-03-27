package com.coolbook.erp.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.hateoas.ResourceSupport;

import java.util.Set;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InvoiceGet extends ResourceSupport {

    @JsonProperty("id")
    private long invoice_Id;
    
    private String customer;
    
    private double total;

    private String date;

    private String invoiceNumber;
    
    private String branch;
    
    Set<InvoiceProductGet> products;
            
    

  
}
