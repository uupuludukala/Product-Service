package com.coolbook.erp.rest.assembler;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.coolbook.erp.entity.CustomerEntity;
import com.coolbook.model.CustomerGet;

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
		customerGet.setFirstName(customerEntity.getFirstName());
		customerGet.setLastName(customerEntity.getLastName());
		customerGet.setAddressLine1(customerEntity.getAddressLine1());
		customerGet.setAddressLine2(customerEntity.getAddressLine2());
		customerGet.setAddressLine3(customerEntity.getAddressLine3());
		customerGet.setMobileNumer(customerEntity.getMobileNumer());
		customerGet.setHomePhone(customerEntity.getHomePhone());
		customerGet.setCreditLimit(customerEntity.getCreditLimit());

		return customerGet;
	}

	protected abstract String getSelfLink(String id);
}
