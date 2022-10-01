package com.coolbook.erp.repository.specs;

import com.coolbook.erp.entity.EasyPaymentEntity;
import com.coolbook.erp.entity.EmployeeEntity;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class EasyPaymentSearchSpecification implements Specification<EasyPaymentEntity> {
    private String searchValue;

    public EasyPaymentSearchSpecification(String searchValue) {
        this.searchValue = searchValue;
    }

    @Override
    public Predicate toPredicate(Root<EasyPaymentEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        Predicate predicate = cb.conjunction();
        if (searchValue != null) {
            Expression<String> invoiceNumber = root.get("invoiceNumber");
            predicate = cb.like(cb.upper(invoiceNumber), "%" + searchValue.toUpperCase() + "%");
        }
        return predicate;
    }
}
