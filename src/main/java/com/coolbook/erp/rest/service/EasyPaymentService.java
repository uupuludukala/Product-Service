package com.coolbook.erp.rest.service;

import com.coolbook.erp.entity.EasyPaymentEntity;
import com.coolbook.erp.repository.EasyPaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EasyPaymentService {

    @Autowired
    private EasyPaymentRepository easyPaymentRepository;

    public long saveEasyPayment(EasyPaymentEntity easyPaymentEntity){
      return  easyPaymentRepository.save(easyPaymentEntity).getId();
    }

    public EasyPaymentEntity getEasyPaymentById(long id) {
        return this.easyPaymentRepository.getOne(id);
    }

    public void cancelEasyPayment(Long id){

    }
}
