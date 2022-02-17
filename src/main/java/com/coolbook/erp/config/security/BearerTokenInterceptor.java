package com.coolbook.erp.config.security;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import com.coolbook.erp.security.SecurityFacade;

/**
 * HTTP interceptor to relay the JWT token
 * <p>
 * As the SecurityContext is bound to a ThreadLocal, in case you plan to use
 * this interceptor from within a {@link com.netflix.hystrix.HystrixCommand} you
 * will need to make sure that the hystrix.shareSecurityContext property is set
 * to true
 */
public class BearerTokenInterceptor implements ClientHttpRequestInterceptor {

	private SecurityFacade securityFacade;

	public BearerTokenInterceptor(SecurityFacade securityFacade) {
		this.securityFacade = securityFacade;
	}

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {
		HttpHeaders headers = request.getHeaders();
		if (headers.get(HttpHeaders.AUTHORIZATION) == null) {
			headers.add(HttpHeaders.AUTHORIZATION, securityFacade.getCurrentAuthorizationToken());
		}
		return execution.execute(request, body);
	}
}
