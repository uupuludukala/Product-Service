package com.coolbook.erp.common.enums;

public enum VendorTypeEnum {
    
    INDIVIDUAL("I"),COMPANY("C");
    
    private String type;

    VendorTypeEnum(String type){
        this.type=type;
    }
    
    
}
