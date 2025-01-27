package com.example.insurance_calculator.rest.controller_v2_test.correct_request;

import com.example.insurance_calculator.rest.controller_v2_test.TravelCalculatePremiumControllerV2TestCase;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

public class V2CorrectRequestTest extends TravelCalculatePremiumControllerV2TestCase {
    //All fields filled. Selected risk - TRAVEL_MEDICAL
    @Test
    public void execute1() throws Exception {
        executeAndCompare("correct request TM", HttpStatus.OK);
    }

    //All fields filled. Selected risk - TRAVEL_CANCELLATION
    @Test
    public void execute24() throws Exception {
        executeAndCompare("correct request TC", HttpStatus.OK);
    }

    //All fields filled. Selected risks: TRAVEL_CANCELLATION, TRAVEL_MEDICAL
    @Test
    public void execute25() throws Exception {
        executeAndCompare("correct request TM, TC", HttpStatus.OK);
    }

    @Override
    protected String getTestCaseFolderPath() {
        return "correct_request/";
    }
}
