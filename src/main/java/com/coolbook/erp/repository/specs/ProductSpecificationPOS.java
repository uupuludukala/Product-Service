package com.coolbook.erp.repository.specs;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import com.coolbook.erp.rest.searchCriteria.ProductCriteria;
import org.springframework.data.jpa.domain.Specification;

import com.coolbook.erp.entity.ProductCategoryEntity;
import com.coolbook.erp.entity.ProductEntity;

public class ProductSpecificationPOS implements Specification<ProductEntity> {

	private ProductCriteria searchCriteria;

	public ProductSpecificationPOS(ProductCriteria searchCriteria) {
		this.searchCriteria = searchCriteria;
	}

	@Override
	public Predicate toPredicate(Root<ProductEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		Predicate predicate = cb.conjunction();
		if (searchCriteria.getId() != null) {
			Expression<String> exp = root.get("id");
			predicate = cb.equal(exp, searchCriteria.getId());
		}
		if (searchCriteria.getProductCode() != null) {
			Expression<String> productCode = root.get("productCode");
			predicate = cb.and(predicate, cb.like(productCode, "%" + searchCriteria.getProductCode() + "%"));
		}

		if (searchCriteria.getProductName() != null) {
			Expression<String> productName = root.get("productName");
			predicate = cb.or(predicate, cb.like(productName, "%" + searchCriteria.getProductName() + "%"));
		}
		
		if (searchCriteria.getBarcode() != null) {
			Expression<String> barcode = root.get("barcode");
			predicate = cb.or(predicate, cb.like(barcode, "%" + searchCriteria.getBarcode() + "%"));
		}
		
		return predicate;
	}

	public Predicate getProductCategoryPredicate(CriteriaQuery<?> query, String productCategoryId, Predicate predicate,
			CriteriaBuilder cb,Expression<ProductCategoryEntity> productCategory) {
		Subquery<?> subQuery = query.subquery(ProductCategoryEntity.class);
		Root<?> rootChild = subQuery.from(ProductCategoryEntity.class);		
		subQuery.select(rootChild.get("id")).where(rootChild.get("id").in(productCategoryId)); // your predicates on children		
		return predicate = cb.and(predicate, productCategory.in(subQuery));
	}

}
