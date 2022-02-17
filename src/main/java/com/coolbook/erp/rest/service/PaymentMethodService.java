

package com.coolbook.erp.rest.service;

import com.coolbook.erp.rest.searchCriteria.PaymentMethodCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.coolbook.erp.entity.PaymentMethodEntity;
import com.coolbook.erp.repository.PaymentMethodRepository;
import com.coolbook.erp.repository.specs.PaymentMethodSpecification;

@Service
public class PaymentMethodService {

	PaymentMethodRepository paymentMethodRepository;

	@Autowired
	PaymentMethodService(PaymentMethodRepository paymentMethodRepository) {
		this.paymentMethodRepository = paymentMethodRepository;
	}

	public long savePaymentMethod(PaymentMethodEntity paymentMethod) {
		return this.paymentMethodRepository.save(paymentMethod).getId();
	}
	
	public void updatePaymentMethod(PaymentMethodEntity paymentMethod,long id) {
		paymentMethod.setId(id);
		this.paymentMethodRepository.save(paymentMethod);
	}
	
	public void deletePaymentMethod(long id) {
		
		this.paymentMethodRepository.delete(id);
	}

	public PaymentMethodEntity getPaymentMethodById(long id) {
		return this.paymentMethodRepository.getOne(id);
	}

	public Page<PaymentMethodEntity> getAllPaymentMethod(Pageable page, PaymentMethodCriteria criteria) {
		PaymentMethodSpecification specification = new PaymentMethodSpecification(criteria);
		return this.paymentMethodRepository.findAll(specification, page);
	}
}

