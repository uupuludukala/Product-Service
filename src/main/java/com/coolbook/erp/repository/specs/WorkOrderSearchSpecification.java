package com.coolbook.erp.repository.specs;

import com.coolbook.erp.entity.WorkOrderEntity;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class WorkOrderSearchSpecification implements Specification<WorkOrderEntity> {
    private String searchValue;

    public WorkOrderSearchSpecification(String searchValue) {
        this.searchValue = searchValue;
    }

    @Override
    public Predicate toPredicate(Root<WorkOrderEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        Predicate predicate = cb.conjunction();
        if (searchValue != null) {
            Expression<String> workOrderNumberExpression = root.get("workOrderNumber");
            predicate = cb.like(cb.upper(workOrderNumberExpression), "%" + searchValue.toUpperCase() + "%");
        }
        return predicate;
    }
}
