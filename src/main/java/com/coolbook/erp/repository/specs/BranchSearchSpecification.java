package com.coolbook.erp.repository.specs;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.springframework.data.jpa.domain.Specification;

import com.coolbook.erp.entity.BranchEntity;
import com.coolbook.erp.entity.CompanyEntity;

public class BranchSearchSpecification implements Specification<BranchEntity> {

	private String searchvalue;
	private long companyId;

	public BranchSearchSpecification(String searchvalue, long companyId) {
		this.searchvalue = searchvalue;
		this.companyId = companyId;
	}

	@Override
	public Predicate toPredicate(Root<BranchEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		
	
		Predicate predicate =null;
		if (searchvalue != null) {
			Expression<String> companyCode = root.get("branchCode");
			predicate =  cb.like(cb.upper(companyCode), "%" + searchvalue.toUpperCase() + "%");
			Expression<String> companyName = root.get("branchName");
			predicate = cb.or(predicate, cb.like(cb.upper(companyName), "%" + searchvalue.toUpperCase() + "%"));
			Expression<String> conatactNumber = root.get("conatactNumber");
			predicate = cb.or(predicate, cb.like(conatactNumber, "%" + searchvalue + "%"));
			if (companyId != 0) {
				Expression<CompanyEntity> company = root.get("company");
				predicate = getCompanyPredicate(root, query, companyId, predicate, cb, company);
			}
		}
		return predicate;
	}

	public Predicate getCompanyPredicate(Root<BranchEntity> root, CriteriaQuery<?> query, long companyId,
			Predicate predicate, CriteriaBuilder cb, Expression<CompanyEntity> company) {
		query.distinct(true);
		Subquery<CompanyEntity> subQuery = query.subquery(CompanyEntity.class);
		Root<CompanyEntity> rootChild = subQuery.from(CompanyEntity.class);
		subQuery.select(rootChild);
		subQuery.where(rootChild.get("id").in(companyId));
		return predicate = cb.and(predicate, cb.equal(subQuery, company));
	}
}