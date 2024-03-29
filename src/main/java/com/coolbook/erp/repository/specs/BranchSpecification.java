package com.coolbook.erp.repository.specs;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.coolbook.erp.entity.BranchEntity;
import com.coolbook.erp.rest.searchCriteria.BranchCriteria;

public class BranchSpecification implements Specification<BranchEntity> {

	private BranchCriteria searchCriteria;

	private String companyCode;


	public BranchSpecification(BranchCriteria searchCriteria,String companyCode) {
		this.searchCriteria = searchCriteria;
		this.companyCode =companyCode;
	}

	@Override
	public Predicate toPredicate(Root<BranchEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		Predicate predicate = cb.conjunction();
		if (searchCriteria.getId() != null) {
			Expression<String> exp = root.get("id");
			predicate = cb.equal(exp, searchCriteria.getId());
		}
		if(!"COOP".equals(companyCode)) {
			Expression<String> exp = root.get("company.companyCode");
			predicate = cb.and(predicate, cb.equal(cb.upper(exp), companyCode));
		}
		if (searchCriteria.getBranchCode() != null) {
			Expression<String> exp = root.get("branchCode");
			predicate = cb.and(predicate, cb.like(cb.upper(exp), "%" + searchCriteria.getBranchCode().toUpperCase() + "%"));
		}
		
		if (searchCriteria.getBranchCode() != null) {
			Expression<String> exp = root.get("branchName");
			predicate = cb.and(predicate, cb.like(cb.upper(exp), "%" + searchCriteria.getBranchName().toUpperCase() + "%"));
		}

		if (searchCriteria.getContactNumber() != null) {
			Expression<String> exp = root.get("contactNumber");
			predicate = cb.and(predicate, cb.like(exp, "%" + searchCriteria.getContactNumber() + "%"));
		}

		if (searchCriteria.getAddress() != null) {
			Expression<String> addres1Exp = root.get("address1");
			predicate = cb.and(predicate, cb.like(addres1Exp, "%" + searchCriteria.getAddress() + "%"));

			Expression<String> addres2Exp = root.get("address2");
			predicate = cb.or(predicate, cb.like(addres2Exp, "%" + searchCriteria.getAddress() + "%"));

			Expression<String> addres3Exp = root.get("address2");
			predicate = cb.or(predicate, cb.like(addres3Exp, "%" + searchCriteria.getAddress() + "%"));
		}
		return predicate;
	}
}
