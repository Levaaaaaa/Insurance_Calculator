package com.example.insurance_calculator.core.services.ack;

import com.example.insurance_calculator.core.api.dto.GeneratedAckDTO;
import org.springframework.stereotype.Service;

@Service
public interface SaveDocGeneratorAckService {
    public void saveAck(GeneratedAckDTO dto);
}
