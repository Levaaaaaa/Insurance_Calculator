package com.example.insurance_calculator.core.api.dto.v2;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PersonRequestV2DTO {

    @NotEmpty(message = "ERROR_CODE_7")
    @Size(max = 200, message = "ERROR_CODE_23")
    String personFirstName;

    @NotEmpty(message = "ERROR_CODE_8")
    @Size(max = 200, message = "ERROR_CODE_24")
    String personLastName;

    @NotNull(message = "ERROR_CODE_11")
    @Past(message = "ERROR_CODE_12")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date personBirthDate;

    @NotEmpty(message = "ERROR_CODE_16")
    private String personIc;

    //todo validation ERROR_CODE_14 - MedicalRiskLimitLevel value = {NOT_SUPPORTED_MEDICAL_RISK_LIMIT_LEVEL} not supported!
    @NotEmpty(message = "ERROR_CODE_13")
    private String medicalRiskLimitLevel;

    @JsonProperty("selected_risks")
 //   @JsonAlias("selected_risks")
    private List<String> selectedRisks;
    private BigDecimal personPremium;

    //todo validation ERROR_CODE_21 - Field personMedicalStatus has unsupported value!
    @NotEmpty(message = "ERROR_CODE_20")
    @JsonAlias("medical_status")
    private String personMedicalStatus;
}
