package com.coolbook.erp.repository.specs;

import com.coolbook.erp.entity.JobCategoryEntity;
import com.coolbook.erp.rest.searchCriteria.JobCategoryCriteria;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class JobCategorySpecification  implements Specification<JobCategoryEntity> {

    private JobCategoryCriteria searchCriteria;

    public JobCategorySpecification(JobCategoryCriteria searchCriteria) {
        this.searchCriteria = searchCriteria;
    }

    @Override
    public Predicate toPredicate(Root<JobCategoryEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        Predicate predicate = cb.conjunction();
        if (searchCriteria.getId() != null) {
            Expression<String> exp = root.get("id");
            predicate = cb.equal(exp, searchCriteria.getId());
        }

        if (searchCriteria.getCategoryName() != null) {
            Expression<String> exp = root.get("categoryName");
            predicate = cb.and(predicate, cb.like(cb.upper(exp), "%" + searchCriteria.getCategoryName().toUpperCase() + "%"));
        }

        if (searchCriteria.getDescription() != null) {
            Expression<String> exp = root.get("description");
            predicate = cb.and(predicate, cb.like(cb.upper(exp), "%" + searchCriteria.getDescription().toUpperCase() + "%"));
        }
        return predicate;
    }
}
