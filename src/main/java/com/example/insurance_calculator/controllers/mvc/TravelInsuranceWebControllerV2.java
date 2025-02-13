package com.example.insurance_calculator.controllers.mvc;

import com.example.insurance_calculator.controllers.rest.common.TravelCalculatePremiumRequestExecutionTimeLogger;
import com.example.insurance_calculator.core.api.command.calculate.TravelCalculatePremiumCoreCommand;
import com.example.insurance_calculator.core.api.command.calculate.TravelCalculatePremiumCoreResult;
import com.example.insurance_calculator.core.api.dto.v2.ConverterV2DTO;
import com.example.insurance_calculator.core.api.dto.v2.TravelCalculatePremiumRequestV2;
import com.example.insurance_calculator.core.api.dto.v2.TravelCalculatePremiumResponseV2;
import com.example.insurance_calculator.core.services.calculate.TravelCalculatePremiumService;
import com.google.common.base.Stopwatch;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Validated
@Controller
@RequestMapping("insurance/travel/web/v2")
public class TravelInsuranceWebControllerV2 {
    @Autowired
    private TravelCalculatePremiumService service;

    @Autowired
    private ConverterV2DTO converterV2DTO;
    @Autowired
    private TravelCalculatePremiumRequestExecutionTimeLogger timeLogger;
    @GetMapping
    public String showForm(ModelMap modelMap) {
        modelMap.addAttribute("request", new TravelCalculatePremiumRequestV2());
        modelMap.addAttribute("response", new TravelCalculatePremiumResponseV2());

        return "travel-calculate-premium-v2";
    }

    @PostMapping
    public String processForm(@ModelAttribute("request") @Valid TravelCalculatePremiumRequestV2 request,
                              ModelMap modelMap) {
        final Stopwatch stopwatch = Stopwatch.createStarted();
        TravelCalculatePremiumCoreResult result;
        TravelCalculatePremiumCoreCommand command = converterV2DTO.buildCommand(request);
        result = service.calculatePremium(command);


        TravelCalculatePremiumResponseV2 response = converterV2DTO.buildResponse(result);

        stopwatch.stop();
        timeLogger.log(stopwatch);

        modelMap.addAttribute("request", request);
        modelMap.addAttribute("response", response);

        return "travel-calculate-premium-v2";
    }
}
