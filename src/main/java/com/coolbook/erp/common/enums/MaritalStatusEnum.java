package com.coolbook.erp.common.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum MaritalStatusEnum {

    MARRIED("M"),SINGLE("S"),OTHER("O");

    private String code;

    MaritalStatusEnum(String code) {
        this.code = code;

    }

    @JsonValue
    @Override
    public String toString()
    {
        return String.valueOf(code);
    }
}
