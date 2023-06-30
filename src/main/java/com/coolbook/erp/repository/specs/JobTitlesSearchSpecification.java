package com.coolbook.erp.repository.specs;

import com.coolbook.erp.entity.JobTitlesEntity;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class JobTitlesSearchSpecification implements Specification<JobTitlesEntity> {

    private String searchValue;

    public JobTitlesSearchSpecification(String searchValue) {
        this.searchValue = searchValue;
    }

    @Override
    public Predicate toPredicate(Root<JobTitlesEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

        Predicate predicate = null;
        if (searchValue != null) {
            Expression<String> jobTitle = root.get("jobTitle");
            predicate = cb.like(cb.upper(jobTitle), "%" + searchValue.toUpperCase() + "%");
            Expression<String> jobDescription = root.get("jobDescription");
            predicate = cb.or(predicate, cb.like(cb.upper(jobDescription), "%" + searchValue.toUpperCase() + "%"));
        }
        return predicate;
    }
}
