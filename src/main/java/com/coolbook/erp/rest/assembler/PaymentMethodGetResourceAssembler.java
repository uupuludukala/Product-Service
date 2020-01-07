
package com.coolbook.erp.rest.assembler;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;


@Component
public class PaymentMethodGetResourceAssembler extends AbstractPaymentMethodResourceAssembler {
	@Override
	protected String getSelfLink(String id) {
		if (!StringUtils.isEmpty(id)) {
			UriComponentsBuilder builder = ServletUriComponentsBuilder.fromHttpUrl(requestURI).query(null);
			if (!builder.build().toString().endsWith("/paymentMethodss")) {
				String branchCurrentURI = builder.build().toString();
				String branchURI = branchCurrentURI.substring(0, branchCurrentURI.indexOf("/paymentMethods") + 9);
				builder = ServletUriComponentsBuilder.fromHttpUrl(branchURI);
			}
			return builder.path("/" + id).build().toUriString();
		}
		return ServletUriComponentsBuilder.fromHttpUrl(requestURI).build().toUriString();
	}
}
