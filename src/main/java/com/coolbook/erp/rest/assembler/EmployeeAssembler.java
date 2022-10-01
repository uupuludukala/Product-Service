package com.coolbook.erp.rest.assembler;

import com.coolbook.erp.common.enums.StatusEnum;
import com.coolbook.erp.entity.EmployeeEntity;
import com.coolbook.erp.model.EmployeeGet;
import com.coolbook.erp.model.EmployeePost;
import org.springframework.stereotype.Component;

@Component
public class EmployeeAssembler {
    public EmployeeGet assembleEmployeeGet(EmployeeEntity employeeEntity) {
        EmployeeGet employeeGet = new EmployeeGet();
        employeeGet.setEmployee_Id(employeeEntity.getId());
        employeeGet.setNicNumber(employeeEntity.getNicNumber());
        employeeGet.setEmployeeName(employeeEntity.getEmployeeName());
        employeeGet.setAddressLine1(employeeEntity.getAddressLine1());
        employeeGet.setAddressLine2(employeeEntity.getAddressLine2());
        employeeGet.setAddressLine3(employeeEntity.getAddressLine3());
        employeeGet.setMobileNumer(employeeEntity.getMobileNumber());
        employeeGet.setHomePhone(employeeEntity.getHomePhone());
        employeeGet.setImageUrl(employeeEntity.getImageUrl());
        employeeGet.setStatus(StatusEnum.getByCode(employeeEntity.getStatus()));
        employeeGet.setBankName(employeeEntity.getBankName());
        employeeGet.setBankBranch(employeeEntity.getBankBranch());
        employeeGet.setAccountNumber(employeeEntity.getAccountNumber());
        
        return employeeGet;
    }

    public EmployeeEntity assembleEmployeeEntity(EmployeePost employeePost) {
        EmployeeEntity employeeEntity=new EmployeeEntity();
        employeeEntity.setNicNumber(employeePost.getNicNumber());
        employeeEntity.setEmployeeName(employeePost.getEmployeeName());
        employeeEntity.setAddressLine1(employeePost.getAddressLine1());
        employeeEntity.setAddressLine2(employeePost.getAddressLine2());
        employeeEntity.setAddressLine3(employeePost.getAddressLine3());
        employeeEntity.setMobileNumber(employeePost.getMobileNumer());
        employeeEntity.setHomePhone(employeePost.getHomePhone());
        employeeEntity.setImageUrl(employeePost.getImageUrl());
        if(employeePost.getStatus()!=null)
            employeeEntity.setStatus(employeePost.getStatus().getCode());
        employeeEntity.setBankName(employeePost.getBankName());
        employeeEntity.setBankBranch(employeePost.getBankBranch());
        employeeEntity.setAccountNumber(employeePost.getAccountNumber());
        return employeeEntity;
    }
}
