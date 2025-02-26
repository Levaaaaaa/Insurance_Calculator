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
    public void generateDocument(AgreementDTO agreementDTO) throws IOException{
        Path file = Path.of(String.format("%s/agreement-proposal-%s.txt", path, agreementDTO.getUuid().toString()));
        ObjectMapper objectMapper = new ObjectMapper();
        //Files.write(file, objectMapper.writeValueAsString(agreementDTO).getBytes(), StandardOpenOption.CREATE);
        try (FileWriter fileWriter = new FileWriter(file.toFile())) {
            fileWriter.write(objectMapper.writeValueAsString(agreementDTO));
        }
    }
}
