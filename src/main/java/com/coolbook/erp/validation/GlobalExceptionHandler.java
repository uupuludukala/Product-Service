package com.coolbook.erp.validation;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.coolbook.erp.exception.ConstraintViolationexception;

@ControllerAdvice
public class GlobalExceptionHandler extends  ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

    protected GlobalExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public MessageSource getMessageSource() {
        return messageSource;
    }
    

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
		apiError.addFieldError("","",e.getMessage(),"");		
		return apiError;
	}
	
	@ExceptionHandler({ ConstraintViolationexception.class })
	@ResponseBody
	@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
	public ApiError handleConstraintViolationexception(ConstraintViolationexception e) {
		ApiError apiError=new ApiError();
        for(org.springframework.validation.FieldError fieldError: e.getFielderrors()){
            apiError.addFieldError(fieldError.getCode(),fieldError.getField(),fieldError.getDefaultMessage(),fieldError.getRejectedValue().toString());
        }
		return apiError;
	}

    @ExceptionHandler({ DataIntegrityViolationException.class })
    @ResponseBody
    @ResponseStatus(HttpStatus.CONFLICT)
    public void handleConstraintViolationexception(DataIntegrityViolationException e) {
        
    }
    
    
	
//	'org.springframework.dao.DataIntegrityViolationException
	
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception exception, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		HttpStatus httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;
		if (BindException.class.isInstance(exception)) {
			List<org.springframework.validation.FieldError> allErrors = ((BindException) exception).getFieldErrors();
			List<FieldError> fieldErrors = new ArrayList<>();

			for (org.springframework.validation.FieldError error : allErrors) {
				fieldErrors.add(new FieldError(error.getCode(), error.getField(), error.getDefaultMessage(),error.getRejectedValue().toString()));
			}
			return new ResponseEntity<>(fieldErrors, httpStatus);
		} else
			return super.handleExceptionInternal(exception, body, headers, httpStatus, request);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiError apiError=new ApiError();
        for(org.springframework.validation.FieldError fieldError: ex.getBindingResult().getFieldErrors()){
            apiError.addFieldError(fieldError.getCode(),fieldError.getField(),
                    messageSource.getMessage(fieldError.getCode(), 
                    new Object[]{
                            fieldError.getField()}
                            , LocaleContextHolder.getLocale()),
                    fieldError.getRejectedValue()!=null?fieldError.getRejectedValue().toString():"");
        }
		return new ResponseEntity<>(apiError, HttpStatus.UNPROCESSABLE_ENTITY);
	}
	
	

	

}
