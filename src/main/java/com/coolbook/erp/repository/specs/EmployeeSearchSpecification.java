package com.coolbook.erp.repository.specs;

import com.coolbook.erp.entity.EmployeeEntity;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class EmployeeSearchSpecification implements Specification<EmployeeEntity> {

    private String searchvalue;

    public EmployeeSearchSpecification(String searchvalue) {
        this.searchvalue = searchvalue;
    }

    @Override
    public Predicate toPredicate(Root<EmployeeEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        Predicate predicate = cb.conjunction();
        if (searchvalue != null) {
            Expression<String> companyCode = root.get("nicNumber");
            predicate = cb.like(cb.upper(companyCode), "%" + searchvalue.toUpperCase() + "%");
            Expression<String> companyName = root.get("employeeName");
            predicate = cb.or(predicate, cb.like(cb.upper(companyName), "%" + searchvalue.toUpperCase() + "%"));
            Expression<String> contactNumber = root.get("mobileNumber");
            predicate = cb.or(predicate, cb.like(contactNumber, "%" + searchvalue + "%"));
        }
        return predicate;
    }

}
