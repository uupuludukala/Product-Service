package com.coolbook.erp.rest.assembler;

import org.springframework.stereotype.Component;

import com.coolbook.erp.entity.CustomerEntity;
import com.coolbook.model.CustomerGet;
import com.coolbook.model.CustomerPost;

@Component
public class CustomerAssembler {
	public CustomerGet essembleCustomerGet(CustomerEntity customerEntity) {
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
	
	public CustomerEntity essembleCustomerentity(CustomerPost customerPost) {
		CustomerEntity customerEntity=new CustomerEntity();
		customerEntity.setNicNumber(customerPost.getNicNumber());
		customerEntity.setFirstName(customerPost.getFirstName());
		customerEntity.setLastName(customerPost.getLastName());
		customerEntity.setAddressLine1(customerPost.getAddressLine1());
		customerEntity.setAddressLine2(customerPost.getAddressLine2());
		customerEntity.setAddressLine3(customerPost.getAddressLine3());
		customerEntity.setMobileNumer(customerPost.getMobileNumer());
		customerEntity.setHomePhone(customerPost.getHomePhone());
		customerEntity.setCreditLimit(customerPost.getCreditLimit());
		return customerEntity;
	}
}