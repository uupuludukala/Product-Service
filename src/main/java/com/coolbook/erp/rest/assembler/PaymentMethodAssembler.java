
package com.coolbook.erp.rest.assembler;

import org.springframework.stereotype.Component;

import com.coolbook.erp.entity.PaymentMethodEntity;
import com.coolbook.model.PaymentMethodGet;
import com.coolbook.model.PaymentMethodPost;

@Component
public class PaymentMethodAssembler {
	public PaymentMethodGet essemblePaymentMethodGet(PaymentMethodEntity paymentMethodEntity) {
		PaymentMethodGet paymentMethodGet = new PaymentMethodGet();
		paymentMethodGet.setPaymentMethod_id(paymentMethodEntity.getId());
		paymentMethodGet.setPaymentMethodName(paymentMethodEntity.getPaymentMethodName());
		paymentMethodGet.setPaymentMethodName(paymentMethodEntity.getPaymentMethodName());
		paymentMethodGet.setCash(paymentMethodEntity.isCash());
		return paymentMethodGet;
	}

	public PaymentMethodEntity essemblePaymentMethodentity(PaymentMethodPost paymentMethodPost) {
		PaymentMethodEntity paymentMethodEntity = new PaymentMethodEntity();
		paymentMethodEntity.setPaymentMethodName(paymentMethodPost.getPaymentMethodName());
		paymentMethodEntity.setCash(paymentMethodPost.isCash());

		return paymentMethodEntity;
	}
}
