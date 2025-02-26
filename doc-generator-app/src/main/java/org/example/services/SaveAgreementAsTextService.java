package org.example.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.AgreementDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;

@Service
public class SaveAgreementAsTextService implements DocGeneratorService{
    @Value("${proposals.directory.path}")
    private String path;

    @Override
    public void generateDocument(AgreementDTO agreementDTO) throws IOException{
        try (FileWriter writer = new FileWriter(path + "/agreement.txt")) {
            ObjectMapper objectMapper = new ObjectMapper();
            writer.write(objectMapper.writeValueAsString(agreementDTO));
        }
    }
}
