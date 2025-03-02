package com.example.insurance_calculator.services.calculate;

import com.example.insurance_calculator.core.api.command.calculate.TravelCalculatePremiumCoreCommand;
import com.example.insurance_calculator.core.api.command.calculate.TravelCalculatePremiumCoreResult;

public interface TravelCalculatePremiumService {
    TravelCalculatePremiumCoreResult calculatePremium(TravelCalculatePremiumCoreCommand command);
}
