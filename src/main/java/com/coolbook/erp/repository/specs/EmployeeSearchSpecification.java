package com.coolbook.erp.repository.specs;

import com.coolbook.erp.entity.EmployeeEntity;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class EmployeeSearchSpecification implements Specification<EmployeeEntity> {

    private String searchValue;

    public EmployeeSearchSpecification(String searchValue) {
        this.searchValue = searchValue;
    }

    @Override
    public Predicate toPredicate(Root<EmployeeEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        Predicate predicate = cb.conjunction();
        if (searchValue != null) {
            Expression<String> companyCode = root.get("nicNumber");
            predicate = cb.like(cb.upper(companyCode), "%" + searchValue.toUpperCase() + "%");
            Expression<String> companyName = root.get("employeeName");
            predicate = cb.or(predicate, cb.like(cb.upper(companyName), "%" + searchValue.toUpperCase() + "%"));
            Expression<String> contactNumber = root.get("mobileNumber");
            predicate = cb.or(predicate, cb.like(contactNumber, "%" + searchValue + "%"));
        }
        return predicate;
    }

}
