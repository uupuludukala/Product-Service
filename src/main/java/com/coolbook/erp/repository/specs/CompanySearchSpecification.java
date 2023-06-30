package com.coolbook.erp.repository.specs;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.coolbook.erp.entity.CompanyEntity;

public class CompanySearchSpecification  implements Specification<CompanyEntity> {

	private String searchValue;

	private String companyCode;

	public CompanySearchSpecification(String searchValue,String companyCode) {
		this.searchValue = searchValue;
		this.companyCode=companyCode;
	}

	@Override
	public Predicate toPredicate(Root<CompanyEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		Predicate predicate =null;

		if (searchValue != null) {
			Expression<String> companyCodeExpression = root.get("companyCode");

			Expression<String> companyName = root.get("companyName");
			predicate =  cb.like(cb.upper(companyName), "%" + searchValue.toUpperCase() + "%");
			Expression<String> contactNumber = root.get("contactNumber");
			predicate = cb.or(predicate, cb.like(contactNumber, "%" + searchValue + "%"));
			if ("COOP".equals(companyCode)){
				predicate = cb.and(predicate,cb.like(cb.upper(companyCodeExpression), "%" + searchValue.toUpperCase() + "%"));
			}else{
				predicate = cb.and(predicate,cb.equal(cb.upper(companyCodeExpression), companyCode));
			}
		}
		return predicate;
	}
}
