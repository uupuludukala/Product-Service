package com.coolbook.erp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.hateoas.ResourceSupport;


import java.util.List;

@Data
public class PurchaseOrderGet extends ResourceSupport {
    
    @JsonProperty("id")
    private long purchase_order_id;
    private String purchaseOrderNumber;
    private String date;
    private List<PurchaseOrderProductGet> purchaseOrderProducts;
    private double total;
    private double totalDiscount;
    private long vendorId;
    private String vendorName;
    private boolean isApproved;
    
}


