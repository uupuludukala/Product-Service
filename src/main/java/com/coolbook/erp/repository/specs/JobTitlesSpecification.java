package com.coolbook.erp.repository.specs;

import com.coolbook.erp.entity.JobTitlesEntity;
import com.coolbook.erp.rest.searchCriteria.JobTitlesCriteria;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class JobTitlesSpecification  implements Specification<JobTitlesEntity> {

    private JobTitlesCriteria searchCriteria;

    public JobTitlesSpecification(JobTitlesCriteria searchCriteria) {
        this.searchCriteria = searchCriteria;
    }

    @Override
    public Predicate toPredicate(Root<JobTitlesEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        Predicate predicate = cb.conjunction();
        if (searchCriteria.getId() != null) {
            Expression<String> exp = root.get("id");
            predicate = cb.equal(exp, searchCriteria.getId());
        }

        if (searchCriteria.getJobTitle() != null) {
            Expression<String> exp = root.get("jobTitle");
            predicate = cb.and(predicate, cb.like(cb.upper(exp), "%" + searchCriteria.getJobTitle().toUpperCase() + "%"));
        }

        if (searchCriteria.getJobDescription() != null) {
            Expression<String> exp = root.get("jobDescription");
            predicate = cb.and(predicate, cb.like(cb.upper(exp), "%" + searchCriteria.getJobDescription().toUpperCase() + "%"));
        }
        return predicate;
    }
}
