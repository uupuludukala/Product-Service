package com.coolbook.erp.repository.specs;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.coolbook.erp.entity.ProductCategoryEntity;
import com.coolbook.erp.rest.searchCriteria.ProductCategoryCriteria;

public class ProductCategorySpecification implements Specification<ProductCategoryEntity>{

	private ProductCategoryCriteria searchCriteria;

	public ProductCategorySpecification(ProductCategoryCriteria searchCriteria) {
		this.searchCriteria = searchCriteria;
	}

	@Override
	public Predicate toPredicate(Root<ProductCategoryEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		Predicate predicate = cb.conjunction();
		if(searchCriteria.getParentCategory()!=null) {
			Expression<String> exp = root.get("parentCategory");
			predicate = cb.equal(exp, searchCriteria.getParentCategory());
		}
		if (searchCriteria.getProductCatCode() != null) {
			Expression<String> comNameExp = root.get("productcatCode");
			predicate = cb.and(predicate, cb.like(comNameExp, "%" + searchCriteria.getProductCatCode() + "%"));
		}

		if (searchCriteria.getProductCatName() != null) {
			Expression<String> contactNumExp = root.get("productcatName");
			predicate = cb.and(predicate, cb.like(contactNumExp, "%" + searchCriteria.getProductCatName() + "%"));
		}
		

		return predicate;
	}

}
