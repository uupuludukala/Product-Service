package com.coolbook.erp.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.hateoas.ResourceSupport;


@Data
@AllArgsConstructor
public class PurchaseOrderProductGet extends ResourceSupport {
    
    @JsonProperty("id")
    private long purchase_order_product_id;
    private long productId;
    private String productCode;
    private String productName;
    private double rate;
    private double discount;
    private double quantity;
    private double amount;
}
