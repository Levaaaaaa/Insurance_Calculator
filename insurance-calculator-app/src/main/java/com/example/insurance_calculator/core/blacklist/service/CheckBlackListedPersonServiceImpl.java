package com.example.insurance_calculator.core.blacklist.service;

import com.example.insurance_calculator.core.api.dto.AgreementDTO;
import com.example.insurance_calculator.core.api.dto.ErrorDTO;
import com.example.insurance_calculator.core.blacklist.service.one_person.CheckOneBlackListedPersonService;
import com.example.insurance_calculator.core.validations.ValidationErrorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientException;

import java.util.List;

@Service
class CheckBlackListedPersonServiceImpl implements CheckBlackListedPersonService{

    @Autowired
    private CheckOneBlackListedPersonService checkOnePersonService;

    @Autowired
    private ValidationErrorFactory errorFactory;

    @Override
    public List<ErrorDTO> checkPersons(AgreementDTO agreementDTO) {
        //process validation error
        //check black listed
        return agreementDTO.getPersons().stream()
                .filter(person -> {
                    return checkOnePersonService.check(person).getPerson().isBlackListed();
                })
                .map(person -> {
                    return errorFactory.buildError("ERROR_CODE_25");
                })
                .toList();
    }
}
