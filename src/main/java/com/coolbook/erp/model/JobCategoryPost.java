package com.coolbook.erp.model;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
public class JobCategoryPost {

    @Valid
    @NotNull
    private String categoryName;

    @Valid
    @NotNull
    private String description;
}
