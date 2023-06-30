package com.coolbook.erp.repository.specs;

import com.coolbook.erp.entity.PettyCashEntity;
import com.coolbook.erp.rest.searchCriteria.PettyCashCriteria;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class PettyCashSpecification implements Specification<PettyCashEntity> {

    private PettyCashCriteria searchCriteria;

    public PettyCashSpecification(PettyCashCriteria searchCriteria) {
        this.searchCriteria = searchCriteria;
    }

    @Override
    public Predicate toPredicate(Root<PettyCashEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        Predicate predicate = cb.conjunction();
        if (searchCriteria.getId() != null) {
            Expression<String> exp = root.get("id");
            predicate = cb.equal(exp, searchCriteria.getId());
        }

        if (searchCriteria.getDate() != null) {
            Expression<String> date = root.get("date");
            predicate = cb.and(predicate, cb.like(cb.upper(date), "%" + searchCriteria.getDate() + "%"));
        }

        if (searchCriteria.getDescription() != null) {
            Expression<String> exp = root.get("description");
            predicate = cb.and(predicate, cb.like(cb.upper(exp), "%" + searchCriteria.getDescription().toUpperCase() + "%"));
        }


        return predicate;
    }
}
