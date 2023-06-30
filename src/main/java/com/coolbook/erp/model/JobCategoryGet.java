package com.coolbook.erp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.hateoas.ResourceSupport;

@Data
public class JobCategoryGet  extends ResourceSupport {
    @JsonProperty("id")
    private long job_Category_id;
    private String categoryName;
    private String description;
}
