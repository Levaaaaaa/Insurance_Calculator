package com.example.insurance_calculator.core.api.dto.v1;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Component
@ConfigurationProperties
public class ValidationError {

    private String errorCode;
    private String description;
//    private String field;
//    private String message;
}
