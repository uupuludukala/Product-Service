package com.coolbook.erp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.hateoas.ResourceSupport;

@Data
public class JobTitlesGet extends ResourceSupport {

    @JsonProperty("id")
    private long job_Titles_id;
    private String jobTitle;

    private String jobDescription;

    private String note;
}
