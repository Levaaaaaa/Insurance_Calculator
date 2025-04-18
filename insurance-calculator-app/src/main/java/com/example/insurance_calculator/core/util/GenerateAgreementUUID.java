package com.example.insurance_calculator.core.util;

import com.example.insurance_calculator.core.api.dto.AgreementDTO;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class GenerateAgreementUUID {
    public UUID generate(AgreementDTO agreement) {
//        return UUID.fromString("12345678-1234-1234-1234-123456789102");
//        return UUID.fromString(agreement.getAgreementDateTo().toString()
//                + agreement.getAgreementDateFrom().toString()
//                );
        return UUID.randomUUID();
//        return UUID.fromString(agreement.toString());
        //UUID.fromString()
        //return "AGR_UUID_#" + agreement.getAgreementDateTo() + agreement.getAgreementDateFrom() + agreement.getPersons().size();
    }
}
