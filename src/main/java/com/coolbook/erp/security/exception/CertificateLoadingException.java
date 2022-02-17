package com.coolbook.erp.security.exception;

public class CertificateLoadingException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public CertificateLoadingException(String message, Throwable cause) {
		super(message, cause);
	}

	public CertificateLoadingException(String message) {
		super(message);
	}
}
