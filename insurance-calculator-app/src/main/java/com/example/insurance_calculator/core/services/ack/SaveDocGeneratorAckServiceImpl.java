package com.example.insurance_calculator.core.services.ack;

import com.example.insurance_calculator.core.api.dto.GeneratedAckDTO;
import com.example.insurance_calculator.persistence.entities.DocGeneratorAckEntity;
import com.example.insurance_calculator.persistence.mappers.DocGeneratorAckMapper;
import com.example.insurance_calculator.persistence.repositories.DocGeneratingErrorAckRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SaveDocGeneratorAckServiceImpl implements SaveDocGeneratorAckService {
    @Autowired
    DocGeneratingErrorAckRepository ackRepository;

    @Autowired
    DocGeneratorAckMapper ackMapper;

    @Override
    public void saveAck(GeneratedAckDTO dto) {
        DocGeneratorAckEntity entity = ackMapper.dtoToEntity(dto);
        ackRepository.save(entity);
    }
}
