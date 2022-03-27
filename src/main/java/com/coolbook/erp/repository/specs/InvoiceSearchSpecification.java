package com.coolbook.erp.repository.specs;

import com.coolbook.erp.entity.InvoiceEntity;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class InvoiceSearchSpecification implements Specification<InvoiceEntity> {
    
    private String searchValue;

    public InvoiceSearchSpecification(String searchValue) {
        this.searchValue = searchValue;
    }

    @Override
    public Predicate toPredicate(Root<InvoiceEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        Predicate predicate = cb.or();
        if (searchValue != null) {
            Expression<String> invoiceNumber = root.get("invoiceNumber");
            predicate = cb.or(predicate, cb.like(cb.upper(invoiceNumber), "%" + searchValue.toUpperCase() + "%"));
        }
        return predicate;
    }
}
