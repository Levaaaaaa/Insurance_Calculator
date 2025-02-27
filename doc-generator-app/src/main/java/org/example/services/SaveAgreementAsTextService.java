package org.example.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.AgreementDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Collection;
import java.util.Collections;

@Service
public class SaveAgreementAsTextService implements DocGeneratorService{
    @Value("${proposals.directory.path}")
    private String path;

    @Override
    public void saveDocumentIntoFile(AgreementDTO agreementDTO) throws IOException{
        Path pathToFile = Path.of(path + "/", buildFileName(agreementDTO));
        ObjectMapper objectMapper = new ObjectMapper();
        try (FileWriter fileWriter = new FileWriter(pathToFile.toFile())) {
            fileWriter.write(objectMapper.writeValueAsString(agreementDTO));
        }
    }

    private String buildFileName(AgreementDTO agreementDTO) {
        return String.format("agreement-proposal-%s.txt", agreementDTO.getUuid().toString());
    }
}
