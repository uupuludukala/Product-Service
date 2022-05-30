package com.coolbook.erp.common.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum PaymentStatusEnum {
    CANCELED("C"),ACTIVE("A");
    private String code;

    PaymentStatusEnum(String code){
        this.code=code;
    }

    @JsonValue
    @Override
    public String toString()
    {
        return String.valueOf(code);
    }

    public static PaymentStatusEnum getByCode(String code){

        for (PaymentStatusEnum statusEnum : PaymentStatusEnum.values())
        {
            if (String.valueOf(statusEnum.code).equals(code))
            {
                return statusEnum;
            }
        }
        return null;
    }
}
