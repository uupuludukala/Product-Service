package com.coolbook.erp.repository.specs;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.coolbook.erp.entity.CustomerEntity;

public class CustomerSearchSpecification implements Specification<CustomerEntity> {

	private String searchvalue;

	public CustomerSearchSpecification(String searchvalue) {
		this.searchvalue = searchvalue;
	}

	@Override
	public Predicate toPredicate(Root<CustomerEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		Predicate predicate = cb.conjunction();
		if (searchvalue != null) {
            Expression<String> companyCode = root.get("nicNumber");
            predicate = cb.like(cb.upper(companyCode), "%" + searchvalue.toUpperCase() + "%");
            Expression<String> companyName = root.get("customerName");
            predicate = cb.or(predicate, cb.like(cb.upper(companyName), "%" + searchvalue.toUpperCase() + "%"));
            Expression<String> contactNumber = root.get("mobileNumber");
            predicate = cb.or(predicate, cb.like(contactNumber, "%" + searchvalue + "%"));
		}
		return predicate;
	}

}