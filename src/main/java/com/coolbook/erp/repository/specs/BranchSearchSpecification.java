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

	private String searchValue;
	private long companyId;

	public BranchSearchSpecification(String searchValue, long companyId) {
		this.searchValue = searchValue;
		this.companyId = companyId;
	}

	@Override
	public Predicate toPredicate(Root<BranchEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		
	
		Predicate predicate =null;
		if (searchValue != null) {
			Expression<String> companyCode = root.get("branchCode");
			predicate =  cb.like(cb.upper(companyCode), "%" + searchValue.toUpperCase() + "%");
			Expression<String> companyName = root.get("branchName");
			predicate = cb.or(predicate, cb.like(cb.upper(companyName), "%" + searchValue.toUpperCase() + "%"));
			Expression<String> contactNumber = root.get("contactNumber");
			predicate = cb.or(predicate, cb.like(contactNumber, "%" + searchValue + "%"));
			if (companyId != 0) {
				Expression<CompanyEntity> company = root.get("company");
				predicate = getCompanyPredicate(root, query, companyId, predicate, cb, company);
			}else{
                Expression<CompanyEntity> company = root.get("company");
                predicate = getCompanySearchPredicate(root, query, searchValue, predicate, cb, company);
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
		return cb.and(predicate, cb.equal(subQuery, company)); 
	}

    public Predicate getCompanySearchPredicate(Root<BranchEntity> root, CriteriaQuery<?> query, String searchValue,
                                         Predicate predicate, CriteriaBuilder cb, Expression<CompanyEntity> company) {        
        Subquery<CompanyEntity> subQuery = query.subquery(CompanyEntity.class);
        Root<CompanyEntity> rootChild = subQuery.from(CompanyEntity.class);
        subQuery.select(rootChild);
        subQuery.where(cb.or(cb.or(cb.like(cb.upper(rootChild.get("companyCode")),"%" + searchValue.toUpperCase() + "%"),
                cb.like(cb.upper(rootChild.get("companyName")),"%" + searchValue.toUpperCase() + "%")),
                cb.like(cb.upper(rootChild.get("contactNumber")),"%" + searchValue.toUpperCase() + "%"))
                );
        return cb.or(predicate, company.in(subQuery));
    }
}