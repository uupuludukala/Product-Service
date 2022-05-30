package com.coolbook.erp.rest.assembler;

import com.coolbook.erp.common.enums.StatusEnum;
import com.coolbook.erp.entity.EmployeeEntity;
import com.coolbook.erp.model.EmployeeGet;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

public abstract class AbstractEmployeeResourceAssembler extends ResourceAssemblerSupport<EmployeeEntity, EmployeeGet> {
    protected String requestURI;

    public AbstractEmployeeResourceAssembler() {
        super(EmployeeEntity.class, EmployeeGet.class);
    }

    @Override
    public EmployeeGet toResource(EmployeeEntity employeeEntity) {
        return createEmployeeJson(employeeEntity);
    }

    private EmployeeGet createEmployeeJson(EmployeeEntity employeeEntity) {
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

    protected abstract String getSelfLink(String id);
}
