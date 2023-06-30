package com.coolbook.erp.common.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum GenderEnum {

    MALE("M"), FE_MALE("F");

    private String code;

    GenderEnum(String code) {
        this.code = code;
    }

    @JsonValue
    @Override
    public String toString()
    {
        return String.valueOf(code);
    }
}
