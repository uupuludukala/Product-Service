package com.coolbook.erp.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include. NON_NULL)
public class WorkOrderComponentGet {

    private ProductGet product;
    private double quantity;
    private double cost;
}
