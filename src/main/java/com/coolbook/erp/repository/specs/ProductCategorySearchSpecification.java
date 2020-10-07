package com.coolbook.erp.repository.specs;

import com.coolbook.erp.entity.ProductCategoryEntity;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class ProductCategorySearchSpecification  implements Specification<ProductCategoryEntity> {

    private String searchValue;
    private long branchId;

    public ProductCategorySearchSpecification(String searchValue, long branchId) {
        this.searchValue = searchValue;
        this.branchId = branchId;
    }

    @Override
    public Predicate toPredicate(Root<ProductCategoryEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {


        Predicate predicate =null;
        if (searchValue != null) {
            Expression<String> productCategoryCode = root.get("productCategoryCode");
            predicate =  cb.like(cb.upper(productCategoryCode), "%" + searchValue.toUpperCase() + "%");
            Expression<String> productCategoryName = root.get("productCategoryName");
            predicate = cb.or(predicate,   cb.like(cb.upper(productCategoryName), "%" + searchValue.toUpperCase() + "%"));        }
        return predicate;
    }


}
