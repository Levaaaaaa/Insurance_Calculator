package com.example.insurance_calculator.rest.controller_v2_test.agreement_errors.country;

import com.example.insurance_calculator.rest.controller_v2_test.TravelCalculatePremiumControllerV2TestCase;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

public class V2AgreementCountryTest extends TravelCalculatePremiumControllerV2TestCase {

    //Country is empty, selected risk - TRAVEL_MEDICAL
    @Test
    public void execute17() throws Exception {
        executeAndCompare("country is empty TM", HttpStatus.BAD_REQUEST);
    }

    //Country is null, selected risk - TRAVEL_MEDICAL
    @Test
    public void execute18() throws Exception {
        executeAndCompare("country is null TM", HttpStatus.BAD_REQUEST);
    }

    //country is not supported
    @Test
    public void execute21() throws Exception {
        executeAndCompare("country is not supported", HttpStatus.BAD_REQUEST);
    }

    //Country is empty, selected risk - TRAVEL_EVACUATION
    @Test
    public void execute22() throws Exception {
        executeAndCompare("country is empty TE", HttpStatus.BAD_REQUEST);
    }

    @Override
    protected String getTestCaseFolderPath() {
        return "agreement_error/country/";
    }
}
