package com.coolbook.erp.repository.specs;

import com.coolbook.erp.entity.EmployeeEntity;
import com.coolbook.erp.rest.searchCriteria.EmployeeCriteria;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class EmployeeSpecification implements Specification<EmployeeEntity> {

    private EmployeeCriteria searchCriteria;

    public EmployeeSpecification(EmployeeCriteria searchCriteria) {
        this.searchCriteria = searchCriteria;
    }

    @Override
    public Predicate toPredicate(Root<EmployeeEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        Predicate predicate = cb.conjunction();
        if (searchCriteria.getNicNumber() != null) {
            Expression<String> nicNumber = root.get("nicNumber");
            predicate = cb.and(predicate, cb.like(nicNumber, "%" + searchCriteria.getNicNumber() + "%"));
        }
        if (searchCriteria.getEmployeeName() != null) {
            Expression<String> employeeName = root.get("employeeName");
            predicate = cb.and(predicate, cb.like(cb.upper(employeeName), "%" + searchCriteria.getEmployeeName().toUpperCase() + "%"));
        }

        if (searchCriteria.getMobileNumer() != null) {
            Expression<String> mobileNumer = root.get("mobileNumer");
            predicate = cb.and(predicate, cb.like(mobileNumer, "%" + searchCriteria.getMobileNumer() + "%"));
        }
        if (searchCriteria.getHomePhone() != null) {
            Expression<String> homePhone = root.get("homePhone");
            predicate = cb.and(predicate, cb.like(homePhone, "%" + searchCriteria.getHomePhone() + "%"));
        }

        return predicate;
    }



}
