package com.coolbook.erp.repository.specs;

import java.util.Collection;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.springframework.data.jpa.domain.Specification;

import com.coolbook.erp.entity.CompanyEntity;
import com.coolbook.erp.entity.ProductCategoryEntity;
import com.coolbook.erp.entity.ProductEntity;
import com.coolbook.erp.rest.searchCriteria.ProductCriteria;

public class ProductSpecification implements Specification<ProductEntity> {

	private ProductCriteria searchCriteria;
	private long companyId;

	public ProductSpecification(ProductCriteria searchCriteria, long companyId) {
		this.searchCriteria = searchCriteria;
		this.companyId = companyId;
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
			predicate = cb.and(predicate,
					cb.like(productCode, "%" + searchCriteria.getProductCode() + "%"));
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
			predicate = getProductCategoryPredicate(query, searchCriteria.getProductCategory(), predicate, cb,
					productCategory);
		}
		if (companyId != 0) {
			Expression<Collection<CompanyEntity>> company = root.get("companies");
			predicate = getCompanyPredicate(root, query, companyId, predicate, cb, company);
		}
		// Expression<String> active = root.get("active");
		// predicate = cb.and(predicate, cb.equal(active, searchCriteria.isActive()));

		return predicate;
	}

	public Predicate getProductCategoryPredicate(CriteriaQuery<?> query, String productCategoryId, Predicate predicate,
			CriteriaBuilder cb, Expression<ProductCategoryEntity> productCategory) {
		Subquery<?> subQuery = query.subquery(ProductCategoryEntity.class);
		Root<?> rootChild = subQuery.from(ProductCategoryEntity.class);
		subQuery.select(rootChild.get("id")).where(rootChild.get("id").in(productCategoryId)); // your predicates on
																								// children
		return predicate = cb.and(predicate, productCategory.in(subQuery));
	}

	public Predicate getCompanyPredicate(Root<ProductEntity> root, CriteriaQuery<?> query, long companyId,
			Predicate predicate, CriteriaBuilder cb, Expression<Collection<CompanyEntity>> company) {
		query.distinct(true);
		Subquery<CompanyEntity> subQuery = query.subquery(CompanyEntity.class);
		Root<CompanyEntity> rootChild = subQuery.from(CompanyEntity.class);
		subQuery.select(rootChild);
		subQuery.where(rootChild.get("id").in(companyId));
		return predicate = cb.and(predicate,cb.isMember(subQuery, company));
	}

}
