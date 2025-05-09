package com.example.insurance_calculator.core.validations.get;

import com.example.insurance_calculator.core.api.dto.ErrorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class GetCommandUUIDValidator {
    @Autowired
    private List<GetCommandUUIDValidation> validations;

    public List<ErrorDTO> validate(String uuid) {
        return validations.stream()
                .map(validation ->
                    {
                        return validation.validate(uuid);
                    }
                    )
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
    }
}
