package com.coolbook.erp.repository.specs;

import com.coolbook.erp.entity.PurchaseOrderEntity;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class PurchaseOrderSearchSpecification implements Specification<PurchaseOrderEntity> {

    private String searchValue;

    public PurchaseOrderSearchSpecification(String searchValue) {
        this.searchValue = searchValue;
    }

    @Override
    public Predicate toPredicate(Root<PurchaseOrderEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        Predicate predicate = cb.conjunction();
        if (searchValue != null) {
            Expression<String> purchaseOrderName = root.get("purchaseOrderNumber");
            predicate = cb.like(cb.upper(purchaseOrderName), "%" + searchValue.toUpperCase() + "%");
            
        }
        return predicate;
    }

}
