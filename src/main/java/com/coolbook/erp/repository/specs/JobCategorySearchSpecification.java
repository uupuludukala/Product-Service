package com.coolbook.erp.repository.specs;

import com.coolbook.erp.entity.JobCategoryEntity;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class JobCategorySearchSpecification implements Specification<JobCategoryEntity> {

    private String searchValue;

    public JobCategorySearchSpecification(String searchValue) {
        this.searchValue = searchValue;
    }

    @Override
    public Predicate toPredicate(Root<JobCategoryEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

        Predicate predicate = null;
        if (searchValue != null) {
            Expression<String> jobTitle = root.get("categoryName");
            predicate = cb.like(cb.upper(jobTitle), "%" + searchValue.toUpperCase() + "%");
            Expression<String> description = root.get("description");
            predicate = cb.or(predicate, cb.like(cb.upper(description), "%" + searchValue.toUpperCase() + "%"));
        }
        return predicate;
    }
}
