package com.coolbook.erp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.hateoas.ResourceSupport;

import java.util.Date;

@Data
public class PettyCashGet extends ResourceSupport {

    @JsonProperty("id")
    private long pettyCash_id;
    private Date date;
    private String description;
    private double amount;
}
