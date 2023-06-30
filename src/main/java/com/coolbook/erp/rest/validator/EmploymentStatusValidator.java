package com.coolbook.erp.rest.validator;

import com.coolbook.erp.common.enums.EmploymentStatusEnum;
import com.coolbook.erp.common.enums.GenderEnum;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class EmploymentStatusValidator implements ConstraintValidator<EmploymentStatusConstraint, EmploymentStatusEnum> {
    private Set<String> allowedValues;

    @Override
    public void initialize(EmploymentStatusConstraint constraintAnnotation) {
        allowedValues = Arrays.stream(constraintAnnotation.enumClass().getEnumConstants())
                .map(Enum::toString)
                .collect(Collectors.toSet());
    }

    @Override
    public boolean isValid(EmploymentStatusEnum value, ConstraintValidatorContext context) {
        if (value == null) {
            return false; // null values are considered valid
        }
        return allowedValues.contains(value.toString());
    }
}
