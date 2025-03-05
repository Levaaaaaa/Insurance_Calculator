package org.example.persistence.mapper;

import org.example.dto.BlackListedPersonDTO;
import org.example.persistence.entities.BlackListedPersonEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BlackListedPersonMapper {
//    @Mapping(target = "id", ignore = true)
//    public BlackListedPersonDTO entityToDto(BlackListedPersonEntity entity);
//
//    @Mapping(target = "blackListed", ignore = true)
//    public BlackListedPersonEntity dtoToEntity(BlackListedPersonDTO dto);
}
