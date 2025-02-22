package com.example.insurance_calculator.core.api.dto.v2;

import com.example.insurance_calculator.core.COUNTRY;
import com.example.insurance_calculator.core.RISKS;
import com.example.insurance_calculator.core.validations.custom_valid_annotations.DateToAfterDateFrom;
import com.example.insurance_calculator.core.validations.custom_valid_annotations.ValidEnum;
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
@DateToAfterDateFrom(message = "ERROR_CODE_5")
public class TravelCalculatePremiumRequestV2 {

//    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "ERROR_CODE_4")
    @Future(message = "ERROR_CODE_3")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date agreementDateTo;

  //  @JsonFormat(pattern = "yyyy_mm-dd")
    @NotNull(message = "ERROR_CODE_2")
    @Future(message = "ERROR_CODE_1")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date agreementDateFrom;

    //todo remove general selected risks, quite person's lists of risks
    @NotEmpty(message = "ERROR_CODE_6")
    @JsonProperty("selected_risks")
    List<@NotBlank(message = "ERROR_CODE_9") @ValidEnum(enumClass = RISKS.class, message = "ERROR_CODE_9") String> selectedRisks;

    @NotBlank(message = "ERROR_CODE_10")
    @ValidEnum(enumClass = COUNTRY.class, message = "ERROR_CODE_15")
    String country;

    @NotEmpty(message = "ERROR_CODE_17")
    List<@Valid PersonRequestV2DTO> persons;

    @NotNull(message = "ERROR_CODE_19")
    BigDecimal cost;
}
