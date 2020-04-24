package com.coolbook.erp.repository.specs;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.coolbook.erp.entity.CompanyEntity;
import com.coolbook.erp.entity.CustomerEntity;

public class CompanySearchSpecification  implements Specification<CompanyEntity> {

	private String searchvalue;

	public CompanySearchSpecification(String searchvalue) {
		this.searchvalue = searchvalue;
	}

	@Override
	public Predicate toPredicate(Root<CompanyEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		Predicate predicate = cb.conjunction();
		if (searchvalue != null) {
			Expression<String> companyCode = root.get("companyCode");
			predicate = cb.or(predicate, cb.like(cb.upper(companyCode), "%" + searchvalue.toUpperCase() + "%"));
			Expression<String> companyName = root.get("companyName");
			predicate = cb.or(predicate, cb.like(cb.upper(companyName), "%" + searchvalue.toUpperCase() + "%"));
			Expression<String> conatactNumber = root.get("conatactNumber");
			predicate = cb.or(predicate, cb.like(conatactNumber, "%" + searchvalue + "%"));
		}
		return predicate;
	}
}
