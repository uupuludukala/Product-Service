package com.coolbook.erp.common.enums;

public enum PettyCashTypeEnum {
    STATIONARY("S"),TRANSPORT("T"),REFRESHMENT("R"),OTHER("O");
    private String type;

    PettyCashTypeEnum(String type){
        this.type=type;
    }
}
