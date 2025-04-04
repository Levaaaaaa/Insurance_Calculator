package com.example.insurance_calculator.persistence.mappers;

import com.example.insurance_calculator.core.api.dto.GeneratedAckDTO;
import com.example.insurance_calculator.persistence.entities.DocGeneratorAckEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-03T17:30:58+0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.5 (Oracle Corporation)"
)
@Component
public class DocGeneratorAckMapperImpl implements DocGeneratorAckMapper {

    @Override
    public DocGeneratorAckEntity dtoToEntity(GeneratedAckDTO dto) {
        if ( dto == null ) {
            return null;
        }

        DocGeneratorAckEntity docGeneratorAckEntity = new DocGeneratorAckEntity();

        docGeneratorAckEntity.setAgreementUUID( dto.getAgreementUUID() );
        docGeneratorAckEntity.setPath( dto.getPath() );

        return docGeneratorAckEntity;
    }

    @Override
    public GeneratedAckDTO entityToDTO(DocGeneratorAckEntity entity) {
        if ( entity == null ) {
            return null;
        }

        GeneratedAckDTO generatedAckDTO = new GeneratedAckDTO();

        generatedAckDTO.setAgreementUUID( entity.getAgreementUUID() );
        generatedAckDTO.setPath( entity.getPath() );

        return generatedAckDTO;
    }
}
