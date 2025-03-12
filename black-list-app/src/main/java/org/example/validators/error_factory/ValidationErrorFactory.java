package org.example.validators.error_factory;

import org.example.validators.ValidationError;
import org.springframework.stereotype.Component;

@Component
public interface ValidationErrorFactory {
    public ValidationError buildError(String errorCode);
}
