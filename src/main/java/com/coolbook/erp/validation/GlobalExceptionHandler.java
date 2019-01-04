package com.coolbook.erp.validation;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler({ HttpServerErrorException.class })
	@ResponseBody
	@ResponseStatus(HttpStatus.CONFLICT)
	public ApiError handleInvalidBranchException(HttpServerErrorException e) {
		//return new FieldError("Object Name", "Field Name", "Message");
		return new ApiError();
	}
	
	@ExceptionHandler({ EntityNotFoundException.class })
	@ResponseBody
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ApiError handleEntityNotFoundException(EntityNotFoundException e) {
		ApiError apiError=new ApiError();
		apiError.fieldErrors.add(new FieldError("","",e.getMessage()));
		return apiError;
	}
	
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception exception, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		HttpStatus httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;
		if (BindException.class.isInstance(exception)) {
			List<org.springframework.validation.FieldError> allErrors = ((BindException) exception).getFieldErrors();
			List<FieldError> fieldErrors = new ArrayList<>();

			for (org.springframework.validation.FieldError error : allErrors) {
				fieldErrors.add(new FieldError(error.getObjectName(), error.getField(), error.getDefaultMessage()));
			}
			return new ResponseEntity<>(fieldErrors, httpStatus);
		} else
			return super.handleExceptionInternal(exception, body, headers, httpStatus, request);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<org.springframework.validation.FieldError> allErrors = ex.getBindingResult().getFieldErrors();
		return new ResponseEntity<>(allErrors, HttpStatus.UNPROCESSABLE_ENTITY);
	}

	

}
