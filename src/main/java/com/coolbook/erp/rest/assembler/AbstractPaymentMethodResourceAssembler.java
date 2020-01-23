
package com.coolbook.erp.rest.assembler;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.coolbook.erp.entity.PaymentMethodEntity;
import com.coolbook.erp.model.PaymentMethodGet;

public abstract class AbstractPaymentMethodResourceAssembler
		extends ResourceAssemblerSupport<PaymentMethodEntity, PaymentMethodGet> {
	protected String requestURI;

	public AbstractPaymentMethodResourceAssembler() {
		super(PaymentMethodEntity.class, PaymentMethodGet.class);
	}

	@Override
	public PaymentMethodGet toResource(PaymentMethodEntity paymentMethodEntity) {
		return createPaymentMethodJson(paymentMethodEntity);
	}

	private PaymentMethodGet createPaymentMethodJson(PaymentMethodEntity paymentMethodEntity) {
		PaymentMethodGet paymentMethodGet = new PaymentMethodGet();
		paymentMethodGet.setPaymentMethod_id(paymentMethodEntity.getId());
		paymentMethodGet.setPaymentMethodName(paymentMethodEntity.getPaymentMethodName());
		paymentMethodGet.setCash(paymentMethodEntity.isCash());

		return paymentMethodGet;
	}

	protected abstract String getSelfLink(String id);
}
