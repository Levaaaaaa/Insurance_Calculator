package com.example.insurance_calculator.core.validations.calculate.agreement;

import com.example.insurance_calculator.core.api.dto.AgreementDTO;
import com.example.insurance_calculator.core.api.dto.ValidationErrorDTO;
import com.example.insurance_calculator.core.repositories.calculate.ClassifierValueRepository;
import com.example.insurance_calculator.core.util.Placeholder;
import com.example.insurance_calculator.core.validations.ValidationErrorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Component
public class AgreementCountryIsSupportedValidation extends TravelAgreementFieldValidationImpl{
    @Autowired
    private ValidationErrorFactory errorFactory;

    @Autowired
    private ClassifierValueRepository classifierValueRepository;

    private List<Placeholder> placeholders;
    @Override
    public Optional<ValidationErrorDTO> validate(AgreementDTO agreement) {
        if (agreement.getCountry() == null || agreement.getCountry().isEmpty()) {
            return Optional.empty();
        }
        initPlaceholders(agreement.getCountry());
        return !existCountry(agreement.getCountry())
                ? Optional.of(errorFactory.buildError("ERROR_CODE_15", placeholders))
                : Optional.empty();
    }

    private boolean existCountry(String countryIc) {
        return classifierValueRepository.findByClassifierTitleAndIc("COUNTRY", countryIc).isPresent();
    }

    private void initPlaceholders(String country) {
        if (placeholders == null) {
            placeholders = new ArrayList<>();
            placeholders.add(new Placeholder("NOT_SUPPORTED_COUNTRY", country));
        }
        else {
            placeholders.clear();
            placeholders.add(new Placeholder("NOT_SUPPORTED_COUNTRY", country));
        }
    }
}
