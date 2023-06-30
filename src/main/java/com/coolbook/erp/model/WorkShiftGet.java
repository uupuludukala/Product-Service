package com.coolbook.erp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.hateoas.ResourceSupport;

@Data
public class WorkShiftGet  extends ResourceSupport
{
    @JsonProperty("id")
    private long work_Shift_id;
    private String workShiftName;

    private String timeFrom;

    private String timeTo;

    private double hoursPerDay;
}
