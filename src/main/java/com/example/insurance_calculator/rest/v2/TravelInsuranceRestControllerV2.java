package com.example.insurance_calculator.rest.v2;

import com.example.insurance_calculator.core.api.command.calculate.TravelCalculatePremiumCoreCommand;
import com.example.insurance_calculator.core.api.command.calculate.TravelCalculatePremiumCoreResult;
import com.example.insurance_calculator.core.api.dto.v2.ConverterV2DTO;
import com.example.insurance_calculator.core.api.dto.v2.TravelCalculatePremiumRequestV2;
import com.example.insurance_calculator.core.api.dto.v2.TravelCalculatePremiumResponseV2;
import com.example.insurance_calculator.core.services.calculate.TravelCalculatePremiumService;
import com.example.insurance_calculator.rest.common.TravelCalculatePremiumRequestExecutionTimeLogger;
import com.google.common.base.Stopwatch;

import jakarta.validation.Valid;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/insurance/travel/api/v2")
public class TravelInsuranceRestControllerV2 {
    @Autowired
    private TravelCalculatePremiumService service;

    @Autowired
    private ConverterV2DTO converterV2;

    @Autowired
    private TravelCalculatePremiumRequestExecutionTimeLogger timeLogger;

    @PostMapping(path = "/",
            consumes = "application/json",
            produces = "application/json")
    public ResponseEntity<?> calculatePremium(
            @RequestBody
            @Valid
            TravelCalculatePremiumRequestV2 request
    ) {
        final Stopwatch stopwatch = Stopwatch.createStarted();

        TravelCalculatePremiumCoreCommand command = converterV2.buildCommand(request);
        TravelCalculatePremiumCoreResult result = service.calculatePremium(command);
        TravelCalculatePremiumResponseV2 response = converterV2.buildResponse(result);
        stopwatch.stop();

        timeLogger.log(stopwatch);

        return ResponseEntity.ok(response);//result;
    }
}
