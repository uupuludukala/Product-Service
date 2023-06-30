package com.coolbook.erp.rest.validator;

import com.coolbook.erp.common.enums.GenderEnum;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class GenderEnumValidator implements ConstraintValidator<GenderConstraint, GenderEnum> {
    private Set<String> allowedValues;

    @Override
    public void initialize(GenderConstraint constraintAnnotation) {
        allowedValues = Arrays.stream(constraintAnnotation.enumClass().getEnumConstants())
                .map(Enum::toString)
                .collect(Collectors.toSet());
    }

    @Override
    public boolean isValid(GenderEnum value, ConstraintValidatorContext context) {
        if (value == null) {
            return false; // null values are considered valid
        }
        return allowedValues.contains(value.toString());
    }
}
