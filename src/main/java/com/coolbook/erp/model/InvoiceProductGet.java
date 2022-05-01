package com.coolbook.erp.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.hateoas.ResourceSupport;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InvoiceProductGet  extends ResourceSupport {

    private String itemCode;
    
    private String description;

    private double amount;

    private double cost;

    private double rate;

    private double total;

    private double discount;

    private double quantity;
    
}
