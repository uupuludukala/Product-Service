package com.coolbook.erp.common.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum EmploymentStatusEnum {
    PERMANENT("PM"),TEMPORARY("TM"), CONFIRM("CF"),CONTRACT("CT");
    private String code;

    EmploymentStatusEnum(String code){
        this.code=code;
    }

    @JsonValue
    @Override
    public String toString()
    {
        return String.valueOf(code);
    }

    public static EmploymentStatusEnum getByCode(String code){

        for (EmploymentStatusEnum statusEnum : EmploymentStatusEnum.values())
        {
            if (String.valueOf(statusEnum.code).equals(code))
            {
                return statusEnum;
            }
        }
        return null;
    }
}
