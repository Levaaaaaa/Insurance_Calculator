package com.example.insurance_calculator.persistence.mappers;

import com.example.insurance_calculator.core.api.dto.GeneratedAckDTO;
import com.example.insurance_calculator.persistence.entities.DocGeneratorAckEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DocGeneratorAckMapper {

    @Mapping(target = "id", ignore = true)
    public DocGeneratorAckEntity dtoToEntity(GeneratedAckDTO dto);

    public GeneratedAckDTO entityToDTO(DocGeneratorAckEntity entity);
}
