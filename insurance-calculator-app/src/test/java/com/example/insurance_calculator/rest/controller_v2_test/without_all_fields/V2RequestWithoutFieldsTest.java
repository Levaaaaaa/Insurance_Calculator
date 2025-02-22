package com.example.insurance_calculator.rest.controller_v2_test.without_all_fields;

import com.example.insurance_calculator.rest.controller_v2_test.TravelCalculatePremiumControllerV2TestCase;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

public class V2RequestWithoutFieldsTest extends TravelCalculatePremiumControllerV2TestCase {

    //all fields not provided, but selected risk is TRAVEL_MEDICAL
    @Test
    public void execute19() throws Exception {
        executeAndCompare("all fields not provided TM", HttpStatus.BAD_REQUEST);
    }

    //all fields not provided
    @Test
    public void execute20() throws Exception {
        executeAndCompare("all fields not provided", HttpStatus.BAD_REQUEST);
    }

    @Override
    protected String getTestCaseFolderPath() {
        return "without_all_fields/";
    }
}
