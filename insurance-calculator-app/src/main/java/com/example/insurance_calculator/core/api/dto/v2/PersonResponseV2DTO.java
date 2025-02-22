package com.example.insurance_calculator.core.api.dto.v2;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.example.insurance_calculator.core.api.dto.RiskDTO;
import com.example.insurance_calculator.core.api.dto.util.BigDecimalSerializer;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PersonResponseV2DTO {
    private String personFirstName;
    private String personLastName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date personBirthDate;

    private UUID personIc;
    private String medicalRiskLimitLevel;

    @JsonProperty("selected_risks")
    private List<RiskDTO> selectedRisks;

    @JsonSerialize(using = BigDecimalSerializer.class)
    //@JsonAlias("premium")
    private BigDecimal personPremium;

    @JsonProperty("medical_status")
    private String personMedicalStatus;
}
