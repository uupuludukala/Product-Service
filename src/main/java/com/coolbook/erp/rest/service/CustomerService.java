package com.coolbook.erp.rest.service;

import java.util.ArrayList;
import java.util.List;

import com.coolbook.erp.rest.searchCriteria.CustomerCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;

import com.coolbook.erp.entity.CustomerEntity;
import com.coolbook.erp.exception.ConstraintViolationexception;
import com.coolbook.erp.repository.CustomerRepository;
import com.coolbook.erp.repository.specs.CustomerSearchSpecification;
import com.coolbook.erp.repository.specs.CustomerSpecification;

@Service
public class CustomerService {

	CustomerRepository customerRepository;

	@Autowired
	CustomerService(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	public long saveCustomer(CustomerEntity customer){
			if (this.customerRepository.getCustomerByNIC(customer.getNicNumber()).size() != 0) {
				List<FieldError> fieldErrors=new ArrayList<FieldError>();
				fieldErrors.add(new FieldError("","nicNumber","NIC Number already Exists"));
				throw new ConstraintViolationexception(fieldErrors);
			}
			return this.customerRepository.save(customer).getId();

	}

	public void updateCustomer(CustomerEntity customer, long id) {
		customer.setId(id);
		this.customerRepository.save(customer);
	}

	public void deleteCustomer(long id) {

		this.customerRepository.delete(id);
	}

	public CustomerEntity getCustomerById(long id) {
		return this.customerRepository.getOne(id);
	}

	public Page<CustomerEntity> getAllCustomer(Pageable page, CustomerCriteria criteria) {
		CustomerSpecification specification = new CustomerSpecification(criteria);
		return this.customerRepository.findAll(specification, page);
	}

	public Page<CustomerEntity> searchCustomer(Pageable page, String searchValue) {
		CustomerSearchSpecification specification = new CustomerSearchSpecification(searchValue);
		return this.customerRepository.findAll(specification, page);
	}
}
