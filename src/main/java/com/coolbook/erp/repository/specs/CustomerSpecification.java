package com.coolbook.erp.repository.specs;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.coolbook.erp.entity.CustomerEntity;
import com.coolbook.erp.rest.searchCriteria.CustomerCriteria;

public class CustomerSpecification  implements Specification<CustomerEntity> {

	private CustomerCriteria searchCriteria;

	public CustomerSpecification(CustomerCriteria searchCriteria) {
		this.searchCriteria = searchCriteria;
	}

	@Override
	public Predicate toPredicate(Root<CustomerEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		Predicate predicate = cb.conjunction();
		if (searchCriteria.getNicNumber() != null) {
			Expression<String> nicNumber = root.get("nicNumber");
			predicate = cb.and(predicate, cb.like(nicNumber, "%" + searchCriteria.getNicNumber() + "%"));
		}
		if (searchCriteria.getCustomerName() != null) {
			Expression<String> customerName = root.get("customerName");
			predicate = cb.and(predicate, cb.like(cb.upper(customerName), "%" + searchCriteria.getCustomerName().toUpperCase() + "%"));
		}
		
		if (searchCriteria.getMobileNumer() != null) {
			Expression<String> mobileNumer = root.get("mobileNumer");
			predicate = cb.and(predicate, cb.like(mobileNumer, "%" + searchCriteria.getMobileNumer() + "%"));
		}
		if (searchCriteria.getHomePhone() != null) {
			Expression<String> homePhone = root.get("homePhone");
			predicate = cb.and(predicate, cb.like(homePhone, "%" + searchCriteria.getHomePhone() + "%"));
		}

		return predicate;
	}

	

}
