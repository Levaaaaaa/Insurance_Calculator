package org.example.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.AgreementDTO;
import org.example.enums.FILE_TYPE;
import org.example.services.build_filename.BuildFilePathService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.FileWriter;
import java.io.IOException;

import static org.example.util.AgreementJsonConverter.jsonFromAgreement;

//@Service
public class SaveAgreementAsTextService implements DocGeneratorService{
    @Autowired
    private BuildFilePathService buildFilePathService;

    @Override
    public void saveDocumentIntoFile(AgreementDTO agreementDTO) throws IOException{
        try (FileWriter fileWriter = new FileWriter(buildFilePathService.buildPath(agreementDTO, FILE_TYPE.TXT))) {
            fileWriter.write(jsonFromAgreement(agreementDTO));
        }
    }


}
