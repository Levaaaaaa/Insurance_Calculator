package com.example.insurance_calculator.persistence.mappers;

import com.example.insurance_calculator.core.api.dto.PersonDTO;
import com.example.insurance_calculator.persistence.entities.agreement.PersonEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    public PersonDTO entityToDto(PersonEntity entity);
    @Mapping(target = "id", ignore = true)
    public PersonEntity dtoToEntity(PersonDTO dto);
}
