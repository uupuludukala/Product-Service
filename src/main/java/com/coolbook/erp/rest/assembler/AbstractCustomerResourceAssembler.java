package com.coolbook.erp.rest.assembler;

import com.coolbook.erp.common.enums.StatusEnum;
import com.coolbook.erp.model.CustomerGet;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.coolbook.erp.entity.CustomerEntity;

public abstract class AbstractCustomerResourceAssembler  extends ResourceAssemblerSupport<CustomerEntity, CustomerGet>{
	protected String requestURI;

	public AbstractCustomerResourceAssembler() {
		super(CustomerEntity.class, CustomerGet.class);
	}

	@Override
	public CustomerGet toResource(CustomerEntity customerEntity) {
		return createCustomerJson(customerEntity);
	}

	private CustomerGet createCustomerJson(CustomerEntity customerEntity) {
		CustomerGet customerGet = new CustomerGet();
		customerGet.setCustomer_Id(customerEntity.getId());
		customerGet.setNicNumber(customerEntity.getNicNumber());
		customerGet.setCustomerName(customerEntity.getCustomerName());
		customerGet.setAddressLine1(customerEntity.getAddressLine1());
		customerGet.setAddressLine2(customerEntity.getAddressLine2());
		customerGet.setAddressLine3(customerEntity.getAddressLine3());
		customerGet.setMobileNumer(customerEntity.getMobileNumber());
		customerGet.setHomePhone(customerEntity.getHomePhone());
		customerGet.setCreditLimit(customerEntity.getCreditLimit());
		customerGet.setImageUrl(customerEntity.getImageUrl());
		customerGet.setStatus(StatusEnum.getByCode(customerEntity.getStatus()));
        customerGet.setCompany(customerEntity.getCompany());
        customerGet.setOccupation(customerEntity.getOccupation());
		return customerGet;
	}

	protected abstract String getSelfLink(String id);
}
