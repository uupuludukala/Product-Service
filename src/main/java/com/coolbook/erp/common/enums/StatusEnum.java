package com.coolbook.erp.common.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum StatusEnum {
    ACTIVE("A"),INACTIVE("I"), BLACKLIST("B");
    private String code;

    StatusEnum(String code){
        this.code=code;
    }
    
    @JsonValue
    @Override
    public String toString()
    {
        return String.valueOf(code);
    }
    
    public static StatusEnum getByCode(String code){

        for (StatusEnum statusEnum : StatusEnum.values())
        {
            if (String.valueOf(statusEnum.code).equals(code))
            {
                return statusEnum;
            }
        }
        return null;
    }
}
