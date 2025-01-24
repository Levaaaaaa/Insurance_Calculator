package com.example.insurance_calculator.core.api.dto.v2;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
public class TravelCalculatePremiumRequestV2 {

//    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "ERROR_CODE_2")
    @Future(message = "ERROR_CODE_1")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date agreementDateTo;

  //  @JsonFormat(pattern = "yyyy_mm-dd")
    @NotNull(message = "ERROR_CODE_4")
    @Future(message = "ERROR_CODE_3")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date agreementDateFrom;

    @NotEmpty(message = "ERROR_CODE_6")
    @JsonProperty("selected_risks")
    List<@NotBlank(message = "ERROR_CODE_9") String> selectedRisks;

    //todo validation ERROR_CODE_15 - Country value = {NOT_SUPPORTED_COUNTRY} not supported!
    @NotEmpty(message = "ERROR_CODE_10")
    String country;

    @NotEmpty(message = "ERROR_CODE_17")
    List<@Valid PersonRequestV2DTO> persons;

    @NotNull(message = "ERROR_CODE_19")
    BigDecimal cost;
}
