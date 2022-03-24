package com.coolbook.erp.repository.specs;

import com.coolbook.erp.entity.InvoiceEntity;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class InvoiceSearchSpecification implements Specification<InvoiceEntity> {
    
    private String searchvalue;

    public InvoiceSearchSpecification(String searchvalue) {
        this.searchvalue = searchvalue;
    }

    @Override
    public Predicate toPredicate(Root<InvoiceEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        Predicate predicate = cb.or();
        if (searchvalue != null) {
            Expression<String> invoiceNumber = root.get("invoiceNumber");
            predicate = cb.or(predicate, cb.like(cb.upper(invoiceNumber), "%" + searchvalue.toUpperCase() + "%"));
            
        }
        return predicate;
    }
}
