package org.example.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.AgreementDTO;

public class AgreementJsonConverter {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String jsonFromAgreement(AgreementDTO agreementDTO) throws JsonProcessingException {
        return objectMapper.writeValueAsString(agreementDTO);
    }

    public static AgreementDTO jsonToAgreement(String jsonAgreement) throws JsonProcessingException {
        return objectMapper.readValue(jsonAgreement, AgreementDTO.class);
    }
}
