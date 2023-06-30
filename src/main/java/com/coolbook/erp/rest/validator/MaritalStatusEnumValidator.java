package com.coolbook.erp.rest.validator;


import com.coolbook.erp.common.enums.MaritalStatusEnum;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class MaritalStatusEnumValidator implements ConstraintValidator<MaritalStatusConstraint, MaritalStatusEnum> {
    private Set<String> allowedValues;

    @Override
    public void initialize(MaritalStatusConstraint constraintAnnotation) {
        allowedValues = Arrays.stream(constraintAnnotation.enumClass().getEnumConstants())
                .map(Enum::toString)
                .collect(Collectors.toSet());
    }

    @Override
    public boolean isValid(MaritalStatusEnum value, ConstraintValidatorContext context) {
        if (value == null) {
            return false; // null values are considered valid
        }
        return allowedValues.contains(value.toString());
    }
}
