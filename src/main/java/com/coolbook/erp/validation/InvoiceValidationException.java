package com.coolbook.erp.validation;

import lombok.Getter;

import java.util.List;

@Getter
public class InvoiceValidationException extends Exception{

    private List<String> validationErrorList ;
    
    public InvoiceValidationException(List<String> validationErrorList){
        this.validationErrorList=validationErrorList;
    }
    
}
