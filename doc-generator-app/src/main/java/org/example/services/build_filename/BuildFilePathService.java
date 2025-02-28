package org.example.services.build_filename;

import org.example.dto.AgreementDTO;
import org.example.enums.FILE_TYPE;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Files;

@Service
public interface BuildFilePathService {
    public File buildPath(AgreementDTO agreementDTO, FILE_TYPE type);
}
