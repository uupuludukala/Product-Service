package com.coolbook.erp.repository.specs;

import com.coolbook.erp.entity.EmployeeAttendanceEntity;
import com.coolbook.erp.entity.EmployeeEntity;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class EmployeeAttendanceSearchSpecification implements Specification<EmployeeAttendanceEntity> {

    private String searchValue;

    private long employeeId;

    public EmployeeAttendanceSearchSpecification(String searchValue, long employeeId) {
        this.searchValue = searchValue;
        this.employeeId = employeeId;
    }

    @Override
    public Predicate toPredicate(Root<EmployeeAttendanceEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {


        Predicate predicate = null;
        if (searchValue != null) {
            Expression<EmployeeEntity> employee = root.get("employee");
            if (employeeId != 0) {
                predicate = getEmployeePredicate(root, query, employeeId, null, cb, employee);
            } else {
                predicate = getEmployeeSearchPredicate(root, query, searchValue, null, cb, employee);
            }
        }
        return predicate;
    }
    public Predicate getEmployeePredicate(Root<EmployeeAttendanceEntity> root, CriteriaQuery<?> query, long companyId,
                                          Predicate predicate, CriteriaBuilder cb, Expression<EmployeeEntity> employee) {
        query.distinct(true);
        Subquery<EmployeeEntity> subQuery = query.subquery(EmployeeEntity.class);
        Root<EmployeeEntity> rootChild = subQuery.from(EmployeeEntity.class);
        subQuery.select(rootChild);
        subQuery.where(rootChild.get("id").in(companyId));
        return cb.and(predicate, cb.equal(subQuery, employee));
    }

    public Predicate getEmployeeSearchPredicate(Root<EmployeeAttendanceEntity> root, CriteriaQuery<?> query, String searchValue,
                                                Predicate predicate, CriteriaBuilder cb, Expression<EmployeeEntity> employee) {
        Subquery<EmployeeEntity> subQuery = query.subquery(EmployeeEntity.class);
        Root<EmployeeEntity> rootChild = subQuery.from(EmployeeEntity.class);
        subQuery.select(rootChild);
        subQuery.where(cb.or(cb.or(cb.like(cb.upper(rootChild.get("nicNumber")),"%" + searchValue.toUpperCase() + "%"),
                        cb.like(cb.upper(rootChild.get("employeeName")),"%" + searchValue.toUpperCase() + "%")),
                cb.like(cb.upper(rootChild.get("mobileNumber")),"%" + searchValue.toUpperCase() + "%"))
        );
        return cb.or(predicate, employee.in(subQuery));
    }
}
