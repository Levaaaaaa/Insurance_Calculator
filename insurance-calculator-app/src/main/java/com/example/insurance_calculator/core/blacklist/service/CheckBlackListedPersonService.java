package com.example.insurance_calculator.core.blacklist.service;

import com.example.insurance_calculator.core.api.dto.AgreementDTO;
import com.example.insurance_calculator.core.api.dto.ErrorDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CheckBlackListedPersonService {
    public List<ErrorDTO> checkPersons(AgreementDTO agreementDTO);
}
