package com.example.insurance_calculator.rest.controller_v2_test.agreement_errors;

import com.example.insurance_calculator.rest.controller_v2_test.TravelCalculatePremiumControllerV2TestCase;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

public class DateToIsLessThanDateFromTest extends TravelCalculatePremiumControllerV2TestCase {

    //dateTo less than dateFrom
    @Test
    public void execute12() throws Exception {
        executeAndCompare("date_to less than date_from", HttpStatus.BAD_REQUEST);
    }

    @Override
    protected String getTestCaseFolderPath() {
        return "agreement_error/";
    }
}
