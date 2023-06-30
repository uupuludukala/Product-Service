package com.coolbook.erp.rest.searchCriteria;

import lombok.Data;
import java.util.Date;

@Data
public class PettyCashCriteria {
    private String id;
    private Date date;
    private String description;
    private double amount;
}
