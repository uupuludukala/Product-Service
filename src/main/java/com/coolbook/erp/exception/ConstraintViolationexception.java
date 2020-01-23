package com.coolbook.erp.exception;

import java.util.List;

import org.springframework.validation.FieldError;

import lombok.Data;

@Data
public class ConstraintViolationexception extends RuntimeException {
	private List<FieldError> fielderrors;

	public ConstraintViolationexception( List<FieldError> fielderrors) {
		this.fielderrors = fielderrors;
	}

	public ConstraintViolationexception(String message, Throwable cause) {
		super(message, cause);
	}
}
