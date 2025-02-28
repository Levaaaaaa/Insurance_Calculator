package org.example.services.build_filename;

import org.example.dto.AgreementDTO;
import org.example.enums.FILE_TYPE;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Path;

@Service
class BuildFilePathImpl implements BuildFilePathService{
    @Value("${proposals.directory.path}")
    private String path;

    @Override
    public File buildPath(AgreementDTO agreementDTO, FILE_TYPE fileType) {
        return Path.of(path + "/", buildFileName(agreementDTO, fileType)).toFile();
    }

    private String buildFileName(AgreementDTO agreementDTO, FILE_TYPE fileType) {
        return String.format("agreement-proposal-%s.%s", agreementDTO.getUuid().toString(), fileType.value);
    }
}
