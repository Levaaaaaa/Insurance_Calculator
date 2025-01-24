package com.example.insurance_calculator.core.services.get;

import com.example.insurance_calculator.core.api.command.get.TravelGetAgreementCoreCommand;
import com.example.insurance_calculator.core.api.command.get.TravelGetAgreementCoreResult;

public interface GetAgreementService {
    public TravelGetAgreementCoreResult getAgreement(TravelGetAgreementCoreCommand command);
}
