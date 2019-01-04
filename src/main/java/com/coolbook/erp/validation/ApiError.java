package com.coolbook.erp.validation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.FieldError;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class ApiError {

	List<FieldError> fieldErrors=new ArrayList<FieldError>();
}
