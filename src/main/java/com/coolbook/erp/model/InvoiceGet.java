package com.coolbook.erp.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.hateoas.ResourceSupport;

import java.util.Set;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InvoiceGet extends ResourceSupport {
    
    private CustomerGet customer;

    private String date;

    private String invoiceNumber;
    
    private BranchGet branch;
    
    Set<InvoiceProductGet> products;
            
    

  
}
