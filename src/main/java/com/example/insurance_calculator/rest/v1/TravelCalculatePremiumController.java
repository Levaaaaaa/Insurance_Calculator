package com.example.insurance_calculator.rest.v1;

import com.google.common.base.Stopwatch;
import com.example.insurance_calculator.core.api.dto.v1.DtoV1Converter;
import com.example.insurance_calculator.core.api.dto.v1.TravelCalculatePremiumRequestV1;
import com.example.insurance_calculator.core.api.dto.v1.TravelCalculatePremiumResponseV1;
import com.example.insurance_calculator.core.services.calculate.TravelCalculatePremiumService;
import com.example.insurance_calculator.rest.common.TravelCalculatePremiumRequestExecutionTimeLogger;
import com.example.insurance_calculator.rest.v1.loggers.TravelCalculatePremiumRequestLogger;
import com.example.insurance_calculator.rest.v1.loggers.TravelCalculatePremiumResponseLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/insurance/travel/api/v1")
public class TravelCalculatePremiumController {
	@Autowired
	private TravelCalculatePremiumRequestLogger requestLogger;
	@Autowired
	private TravelCalculatePremiumResponseLogger responseLogger;
	@Autowired
	private TravelCalculatePremiumRequestExecutionTimeLogger requestTimeLogger;
	@Autowired
	private TravelCalculatePremiumService service;

	@Autowired
	private DtoV1Converter converter;

	@PostMapping(path = "/",
			consumes = "application/json",
			produces = "application/json")
	public TravelCalculatePremiumResponseV1 calculatePremium(@RequestBody TravelCalculatePremiumRequestV1 request) {
		final Stopwatch stopWatch = Stopwatch.createStarted();
		TravelCalculatePremiumResponseV1 response = converter.processRequest(request, service);
		stopWatch.stop();
		requestTimeLogger.log(stopWatch);
		return response;
		//calculatePremiumService.calculatePremium(request);
	}


}