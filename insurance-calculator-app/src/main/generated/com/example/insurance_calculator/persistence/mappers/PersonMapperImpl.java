package com.example.insurance_calculator.persistence.mappers;

import com.example.insurance_calculator.core.api.dto.PersonDTO;
import com.example.insurance_calculator.persistence.entities.agreement.PersonEntity;
import java.sql.Date;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-03T17:30:58+0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.5 (Oracle Corporation)"
)
@Component
public class PersonMapperImpl implements PersonMapper {

    @Override
    public PersonDTO entityToDto(PersonEntity entity) {
        if ( entity == null ) {
            return null;
        }

        PersonDTO personDTO = new PersonDTO();

        personDTO.setPersonFirstName( entity.getPersonFirstName() );
        personDTO.setPersonLastName( entity.getPersonLastName() );
        personDTO.setPersonIc( entity.getPersonIc() );
        personDTO.setPersonBirthDate( entity.getPersonBirthDate() );

        return personDTO;
    }

    @Override
    public PersonEntity dtoToEntity(PersonDTO dto) {
        if ( dto == null ) {
            return null;
        }

        PersonEntity personEntity = new PersonEntity();

        personEntity.setPersonFirstName( dto.getPersonFirstName() );
        personEntity.setPersonLastName( dto.getPersonLastName() );
        personEntity.setPersonIc( dto.getPersonIc() );
        if ( dto.getPersonBirthDate() != null ) {
            personEntity.setPersonBirthDate( new Date( dto.getPersonBirthDate().getTime() ) );
        }

        return personEntity;
    }
}
