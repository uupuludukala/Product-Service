package com.coolbook.erp.repository.specs;

import com.coolbook.erp.entity.PurchaseOrderEntity;
import com.coolbook.erp.rest.searchCriteria.PurchaseOrderCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class PurchaseOrderSpecification implements Specification<PurchaseOrderEntity> {

    @Autowired
    private PurchaseOrderCriteria searchCriteria;
    
    @Override
    public Predicate toPredicate(Root<PurchaseOrderEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        Predicate predicate = cb.conjunction();
        if (searchCriteria.getPurchaseOrderNumber() != null) {
            Expression<String> vendorName = root.get("purchaseOrderNumber");
            predicate = cb.and(predicate, cb.like(cb.upper(vendorName), "%" + searchCriteria.getPurchaseOrderNumber().toUpperCase() + "%"));
            
            
        }
        return predicate;
    }
}
