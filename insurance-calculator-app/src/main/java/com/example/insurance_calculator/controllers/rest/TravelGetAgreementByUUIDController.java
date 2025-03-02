package com.example.insurance_calculator.controllers.rest;

import com.example.insurance_calculator.core.api.command.get.TravelGetAgreementCoreCommand;
import com.example.insurance_calculator.core.api.command.get.TravelGetAgreementCoreResult;
import com.example.insurance_calculator.services.get.GetAgreementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/insurance/travel/api/internal/agreement")
public class TravelGetAgreementByUUIDController {

    @Autowired
    private GetAgreementService service;
    @GetMapping(path = "/{uuid}",
                consumes = "application/json",
                produces = "application/json")
    public TravelGetAgreementCoreResult getAgreement(@PathVariable("uuid") String uuid) {
        TravelGetAgreementCoreCommand command = new TravelGetAgreementCoreCommand(uuid);
        TravelGetAgreementCoreResult result = service.getAgreement(command);
        return result;
        /*
        return new AgreementDTO(Date.valueOf("2050-02-02"),
                Date.valueOf("2050-02-05"),
                uuid,
                "COUNTRY",
                BigDecimal.ONE,
                List.of("TRAVEL_EVACUATION"),
                List.of());

         */
    }
}
