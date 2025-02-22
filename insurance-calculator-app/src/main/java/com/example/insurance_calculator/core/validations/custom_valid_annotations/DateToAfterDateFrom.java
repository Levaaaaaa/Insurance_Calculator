package com.example.insurance_calculator.core.validations.custom_valid_annotations;

import com.example.insurance_calculator.core.validations.custom_validators.DateToAfterDateFromValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy = DateToAfterDateFromValidator.class)
public @interface DateToAfterDateFrom {
    String message() default "date from must be before date to";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
