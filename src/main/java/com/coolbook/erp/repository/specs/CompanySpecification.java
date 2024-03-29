package com.coolbook.erp.repository.specs;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.coolbook.erp.rest.searchCriteria.CompanyCriteria;
import org.springframework.data.jpa.domain.Specification;

import com.coolbook.erp.entity.CompanyEntity;

public class CompanySpecification implements Specification<CompanyEntity> {

	private CompanyCriteria searchCriteria;

	public CompanySpecification(CompanyCriteria searchCriteria) {
		this.searchCriteria = searchCriteria;
	}

	@Override
	public Predicate toPredicate(Root<CompanyEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		Predicate predicate = cb.conjunction();
		if (searchCriteria.getCompanyId() != null) {
			Expression<String> exp = root.get("id");
			predicate = cb.equal(exp, searchCriteria.getCompanyId());
		}
		if (searchCriteria.getCompanyName() != null) {
			Expression<String> comNameExp = root.get("companyName");
			predicate = cb.and(predicate, cb.like(cb.upper(comNameExp), "%" + searchCriteria.getCompanyName().toUpperCase() + "%"));
		}

		if (searchCriteria.getContactNumber() != null) {
			Expression<String> contactNumExp = root.get("contactNumber");
			predicate = cb.and(predicate, cb.like(contactNumExp, "%" + searchCriteria.getContactNumber() + "%"));
		}

		return predicate;
	}

}
