package com.coolbook.erp.repository.specs;

import com.coolbook.erp.entity.WorkShiftEntity;
import com.coolbook.erp.rest.searchCriteria.WorkShiftCriteria;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class WorkShiftSpecification implements Specification<WorkShiftEntity> {

    private WorkShiftCriteria searchCriteria;

    public WorkShiftSpecification(WorkShiftCriteria searchCriteria) {
        this.searchCriteria = searchCriteria;
    }

    @Override
    public Predicate toPredicate(Root<WorkShiftEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        Predicate predicate = cb.conjunction();
        if (searchCriteria.getId() != null) {
            Expression<String> exp = root.get("id");
            predicate = cb.equal(exp, searchCriteria.getId());
        }

        if (searchCriteria.getWorkShift() != null) {
            Expression<String> exp = root.get("workShift");
            predicate = cb.equal(exp, searchCriteria.getWorkShift());
        }
        return predicate;
    }
}
