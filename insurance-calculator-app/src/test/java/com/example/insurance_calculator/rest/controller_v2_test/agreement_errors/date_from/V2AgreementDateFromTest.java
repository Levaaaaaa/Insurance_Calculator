package com.example.insurance_calculator.rest.controller_v2_test.agreement_errors.date_from;

import com.example.insurance_calculator.rest.controller_v2_test.TravelCalculatePremiumControllerV2TestCase;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

public class V2AgreementDateFromTest extends TravelCalculatePremiumControllerV2TestCase {

    //date_from is null
    @Test
    public void execute8() throws Exception {
        executeAndCompare("date_from is null", HttpStatus.BAD_REQUEST);
    }

    //date_from is in the past
    @Test
    public void execute10() throws Exception {
        executeAndCompare("date_from is in the past", HttpStatus.BAD_REQUEST);
    }

    @Override
    protected String getTestCaseFolderPath() {
        return "agreement_error/date_from/";
    }
}
