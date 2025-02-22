package com.example.insurance_calculator.rest.controller_v2_test.agreement_errors.medical_risk_limit_level;

import com.example.insurance_calculator.rest.controller_v2_test.TravelCalculatePremiumControllerV2TestCase;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

public class V2AgreementMedicalRiskLimitLevelTest extends TravelCalculatePremiumControllerV2TestCase {

    //medicalRiskLimitLevelIc is not supported. Selected risk - TRAVEL_MEDICAL
    @Test
    public void execute23() throws Exception {
        executeAndCompare("not supported ic", HttpStatus.BAD_REQUEST);
    }

    @Override
    protected String getTestCaseFolderPath() {
        return "agreement_error/medical_risk_limit_level/";
    }
}
