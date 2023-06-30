package com.coolbook.erp.model;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
public class JobTitlesPost {

    @Valid
    @NotNull
    private String jobTitle;

    @Valid
    @NotNull
    private String jobDescription;

    private String note;
}
