package com.example.insurance_calculator.core.api.dto;

import com.example.insurance_calculator.core.api.dto.util.BigDecimalSerializer;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AgreementDTO {


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Europe/Minsk")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date agreementDateFrom;

    //todo add validation dateTo after dateFrom (ERROR_CODE_5)

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Europe/Minsk")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date agreementDateTo;

    //@JsonAlias(value = "uuid")
    private UUID uuid;

    private String country;

    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal agreementPremium;

    //todo validation ERROR_CODE_9 - Risk Type ic = {NOT_EXISTING_RISK_TYPE} not supported!
    @JsonAlias(value = "selected_risks")
    @JsonProperty("selected_risks")
    private List<String> selectedRisks;

    @JsonProperty("persons")
    private List<PersonDTO> persons;

    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal cost;
}