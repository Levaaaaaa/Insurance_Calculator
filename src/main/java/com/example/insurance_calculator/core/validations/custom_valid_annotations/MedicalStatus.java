package com.example.insurance_calculator.core.validations.custom_valid_annotations;

import com.example.insurance_calculator.core.validations.custom_validators.PersonMedicalStatusValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PersonMedicalStatusValidator.class)
public @interface MedicalStatus {
    String message() default "Invalid person medical status";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
