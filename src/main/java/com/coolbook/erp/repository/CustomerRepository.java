package com.coolbook.erp.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.coolbook.erp.entity.CustomerEntity;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long>, JpaSpecificationExecutor<CustomerEntity> {
	@Query(value="from CustomerEntity c where c.nicNumber =?1")
	ArrayList<CustomerEntity> getCustomerByNIC(String nicNumber);

}
