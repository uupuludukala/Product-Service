package com.coolbook.erp.repository.specs;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.coolbook.erp.entity.UserEntity;
import com.coolbook.erp.rest.searchCriteria.UserCriteria;

public class UserSpecification implements Specification<UserEntity> {

	private UserCriteria searchCriteria;

	public UserSpecification(UserCriteria searchCriteria) {
		this.searchCriteria = searchCriteria;
	}

	@Override
	public Predicate toPredicate(Root<UserEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		Predicate predicate = cb.conjunction();
		if (searchCriteria.getId() != null) {
			Expression<String> exp = root.get("id");
			predicate = cb.equal(exp, searchCriteria.getId());
		}
		if (searchCriteria.getUserName() != null) {
			Expression<String> comNameExp = root.get("userName");
			predicate = cb.and(predicate, cb.like(comNameExp, "%" + searchCriteria.getUserName() + "%"));
		}

		return predicate;
	}

}
