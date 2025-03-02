package com.example.insurance_calculator.core.api.dto;

import lombok.*;
import lombok.experimental.Accessors;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class GeneratedAckDTO {
    private UUID agreementUUID;
    private String path;
}
