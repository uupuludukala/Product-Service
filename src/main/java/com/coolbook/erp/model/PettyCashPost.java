package com.coolbook.erp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Column;
import java.util.Date;

@Data
public class PettyCashPost {

    @JsonProperty("date")
    private Date date;

    @JsonProperty("description")
    private String description;

    @JsonProperty("amount")
    private double amount;
}
