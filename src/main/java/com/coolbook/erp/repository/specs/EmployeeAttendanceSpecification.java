package com.coolbook.erp.repository.specs;

import com.coolbook.erp.entity.EmployeeAttendanceEntity;
import com.coolbook.erp.rest.searchCriteria.EmployeeAttendanceCriteria;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class EmployeeAttendanceSpecification implements Specification<EmployeeAttendanceEntity> {

    private EmployeeAttendanceCriteria searchCriteria;

    public EmployeeAttendanceSpecification(EmployeeAttendanceCriteria searchCriteria) {
        this.searchCriteria = searchCriteria;
    }

    @Override
    public Predicate toPredicate(Root<EmployeeAttendanceEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        Predicate predicate = cb.conjunction();

        return predicate;
    }
}
