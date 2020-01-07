
package com.coolbook.erp.repository.specs;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.coolbook.erp.entity.PaymentMethodEntity;
import com.coolbook.erp.rest.searchCriteria.PaymentMethodCriteria;

public class PaymentMethodSpecification implements Specification<PaymentMethodEntity> {

	private PaymentMethodCriteria searchCriteria;

	public PaymentMethodSpecification(PaymentMethodCriteria searchCriteria) {
		this.searchCriteria = searchCriteria;
	}

	@Override
	public Predicate toPredicate(Root<PaymentMethodEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		Predicate predicate = cb.conjunction();
		if (searchCriteria.getPaymentMethodName() != null) {
			Expression<String> paymentMethodName = root.get("paymentMethodName");
			predicate = cb.and(predicate,
					cb.like(paymentMethodName, "%" + searchCriteria.getPaymentMethodName() + "%"));
		}

		return predicate;
	}

}
