package org.example.services;

import org.example.dto.AgreementDTO;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface DocGeneratorService {
    public void saveDocumentIntoFile(AgreementDTO agreementDTO) throws IOException;
}
