package com.coolbook.erp.common.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum EmployeePaymentFrequencyEnum {
    HOURLY("H"),DAILY("D"),WEEKLY("W"),MONTHLY("M");
    String code;


    EmployeePaymentFrequencyEnum(String code){
        this.code=code;
    }

    @JsonValue
    @Override
    public String toString()
    {
        return String.valueOf(code);
    }
}
