package com.coolbook.erp.repository.specs;

import com.coolbook.erp.entity.WorkShiftEntity;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class WorkShiftSearchSpecification implements Specification<WorkShiftEntity> {

    private String searchValue;

    public WorkShiftSearchSpecification(String searchValue) {
        this.searchValue = searchValue;
    }

    @Override
    public Predicate toPredicate(Root<WorkShiftEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

        Predicate predicate = null;
        if (searchValue != null) {
            Expression<String> workShift = root.get("workShiftName");
            predicate = cb.like(cb.upper(workShift), "%" + searchValue.toUpperCase() + "%");
        }
        return predicate;
    }
}
