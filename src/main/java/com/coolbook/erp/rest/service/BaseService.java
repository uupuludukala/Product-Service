package com.coolbook.erp.rest.service;

import com.coolbook.erp.entity.BaseEntity;
import com.coolbook.erp.security.SecurityFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class BaseService {

    @Autowired
    SecurityFacade securityFacade;
    void setMetaData(BaseEntity entity, BaseEntity original){
        if(original==null) {
            entity.setCreatedDate(new Date());
            entity.setCreatedBy(securityFacade.getCurrentUser().getUserId());
        }else{
            entity.setCreatedBy(original.getLastModifiedBy());
            entity.setCreatedDate(original.getLastModifiedDate());
        }
        entity.setLastModifiedBy(securityFacade.getCurrentUser().getUserId());
        entity.setLastModifiedDate(new Date());
    }

}
