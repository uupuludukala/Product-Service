package com.coolbook.erp.repository.specs;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.springframework.data.jpa.domain.Specification;

import com.coolbook.erp.entity.ProductCategoryEntity;
import com.coolbook.erp.entity.ProductEntity;
import com.coolbook.erp.rest.searchCriteria.ProductCriteria;

public class ProductSpecification implements Specification<ProductEntity> {

	private ProductCriteria searchCriteria;

	public ProductSpecification(ProductCriteria searchCriteria) {
		this.searchCriteria = searchCriteria;
	}

	@Override
	public Predicate toPredicate(Root<ProductEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		Predicate predicate = cb.conjunction();
		if (searchCriteria.getId() != null) {
			Expression<String> exp = root.get("id");
			predicate = cb.equal(exp, searchCriteria.getId());
		}
		if (searchCriteria.getInternalReference() != null) {
			Expression<String> internalReference = root.get("internalReference");
			predicate = cb.and(predicate, cb.like(internalReference, "%" + searchCriteria.getInternalReference() + "%"));
		}

		if (searchCriteria.getProductName() != null) {
			Expression<String> productName = root.get("productName");
			predicate = cb.and(predicate, cb.like(productName, "%" + searchCriteria.getProductName() + "%"));
		}
		if (searchCriteria.getProductType() != null) {
			Expression<String> productType = root.get("productType");
			predicate = cb.and(predicate, cb.like(productType, "%" + searchCriteria.getProductType() + "%"));
		}
		if (searchCriteria.getBarcode() != null) {
			Expression<String> barcode = root.get("barcode");
			predicate = cb.and(predicate, cb.like(barcode, "%" + searchCriteria.getBarcode() + "%"));
		}
		if (searchCriteria.getProductCategory() != null && !"".equals(searchCriteria.getProductCategory())) {
			Expression<ProductCategoryEntity> productCategory = root.get("productCategory");
			predicate=getProductCategoryPredicate(query, searchCriteria.getProductCategory(), predicate,cb,productCategory);
		}
//		Expression<String> active = root.get("active");
//		predicate = cb.and(predicate, cb.equal(active, searchCriteria.isActive()));

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
