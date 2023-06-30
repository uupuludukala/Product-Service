package com.coolbook.erp.model;

import lombok.Data;

@Data
public class WorkShiftPost {

    private String workShiftName;

    private String timeFrom;

    private String timeTo;

    private double hoursPerDay;
}
