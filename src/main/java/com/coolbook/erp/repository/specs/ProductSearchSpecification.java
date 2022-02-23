package com.coolbook.erp.repository.specs;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.coolbook.erp.entity.ProductEntity;

public class ProductSearchSpecification  implements Specification<ProductEntity> {
	private String searchvalue;

	public ProductSearchSpecification(String searchvalue) {
		this.searchvalue = searchvalue;
	}

	@Override
	public Predicate toPredicate(Root<ProductEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		Predicate predicate = cb.or();
		if (searchvalue != null) {
//            Expression<String> companyCode = root.get("branchCode");
//            predicate =  cb.like(cb.upper(companyCode), "%" + searchValue.toUpperCase() + "%");
			Expression<String> barcode = root.get("barcode");
			predicate = cb.or(predicate, cb.like(cb.upper(barcode), "%" + searchvalue.toUpperCase() + "%"));
			Expression<String> productCode = root.get("productCode");
			predicate = cb.or(predicate, cb.like(cb.upper(productCode), "%" + searchvalue.toUpperCase() + "%"));
			Expression<String> productName = root.get("productName");
			predicate = cb.or(predicate, cb.like(cb.upper(productName), "%" + searchvalue.toUpperCase() + "%"));
		}
		return predicate;
	}
}
