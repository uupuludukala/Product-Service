package com.coolbook.erp.rest.assembler;

import com.coolbook.erp.entity.EasyPaymentEntity;
import com.coolbook.erp.entity.EmployeeEntity;
import com.coolbook.erp.model.EasyPaymentGet;
import com.coolbook.erp.model.EasyPaymentPost;
import com.coolbook.erp.model.EmployeeGet;

public class EasyPaymentAssembler {

    public EasyPaymentGet assembleEasyPaymentGet(EasyPaymentEntity easyPaymentEntity) {
        EasyPaymentGet easyPaymentGet=new EasyPaymentGet();
        return easyPaymentGet;
    }

    public EasyPaymentEntity assembleEasyPaymentEntity(EasyPaymentPost easyPaymentPost) {
        EasyPaymentEntity easyPaymentEntity=new EasyPaymentEntity();
        return easyPaymentEntity;
    }
}
