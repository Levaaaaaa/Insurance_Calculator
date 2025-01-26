package com.example.insurance_calculator.core.api.dto.v2;

import com.example.insurance_calculator.core.MEDICAL_RISK_LIMIT_LEVEL;
import com.example.insurance_calculator.core.RISKS;
import com.example.insurance_calculator.core.TE_PERSON_MEDICAL_STATUS;
import com.example.insurance_calculator.core.validations.custom_valid_annotations.MedicalStatus;
import com.example.insurance_calculator.core.validations.custom_valid_annotations.ValidEnum;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@MedicalStatus(message = "ERROR_CODE_20")
public class PersonRequestV2DTO {

    @NotBlank(message = "ERROR_CODE_7")
    @Size(max = 200, message = "ERROR_CODE_23")
    String personFirstName;

    @NotBlank(message = "ERROR_CODE_8")
    @Size(max = 200, message = "ERROR_CODE_24")
    String personLastName;

    @NotNull(message = "ERROR_CODE_11")
    @Past(message = "ERROR_CODE_12")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date personBirthDate;

//    @NotBlank(message = "ERROR_CODE_16")
    private String personIc;

    @NotBlank(message = "ERROR_CODE_13")
    @ValidEnum(enumClass = MEDICAL_RISK_LIMIT_LEVEL.class, message = "ERROR_CODE_14")
    private String medicalRiskLimitLevel;

    @JsonProperty("selected_risks")
    private List<@ValidEnum(enumClass = RISKS.class, message = "ERROR_CODE_9") String> selectedRisks;

    private BigDecimal personPremium;

    @ValidEnum(enumClass = TE_PERSON_MEDICAL_STATUS.class, message = "ERROR_CODE_21")
    @JsonAlias("medical_status")
    private String personMedicalStatus;
}
