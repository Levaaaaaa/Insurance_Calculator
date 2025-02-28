package org.example.services;

import com.itextpdf.text.DocumentException;
import org.example.dto.AgreementDTO;
import org.example.enums.FILE_TYPE;
import org.example.services.build_filename.BuildFilePathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static org.example.util.AgreementJsonConverter.jsonFromAgreement;
import static org.example.util.SavePdf.saveAsPdf;

@Service
public class SaveAgreementAsPdfService implements DocGeneratorService{
    @Autowired
    private BuildFilePathService buildFilePathService;

    @Override
    public void saveDocumentIntoFile(AgreementDTO agreementDTO) throws IOException {
        try {
            saveAsPdf(buildFilePathService.buildPath(agreementDTO, FILE_TYPE.PDF), jsonFromAgreement(agreementDTO));
        } catch (DocumentException e) {
            throw new IOException("Was caught document exception with message `" + e.getMessage());
        }
    }
}
