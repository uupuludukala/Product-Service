package com.coolbook.erp.repository.specs;


import com.coolbook.erp.entity.WorkOrderEntity;
import com.coolbook.erp.rest.searchCriteria.WorkOrderCriteria;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class WorkOrderSpecification implements Specification<WorkOrderEntity> {

    private WorkOrderCriteria searchCriteria;

    public WorkOrderSpecification(WorkOrderCriteria searchCriteria) {
        this.searchCriteria = searchCriteria;
    }

    @Override
    public Predicate toPredicate(Root<WorkOrderEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        Predicate predicate = cb.conjunction();


        if (searchCriteria.getWorkOrderNumber() != null) {
            Expression<String> workOrderNumber = root.get("workOrderNumber");
            predicate = cb.and(predicate, cb.like(cb.upper(workOrderNumber), "%" + searchCriteria.getWorkOrderNumber().toUpperCase() + "%"));
        }

        return predicate;
    }



}
