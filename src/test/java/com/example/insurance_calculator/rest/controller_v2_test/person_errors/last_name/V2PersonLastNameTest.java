package com.example.insurance_calculator.rest.controller_v2_test.person_errors.last_name;

import com.example.insurance_calculator.rest.controller_v2_test.TravelCalculatePremiumControllerV2TestCase;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

public class V2PersonLastNameTest extends TravelCalculatePremiumControllerV2TestCase {

    //lastName is null
    @Test
    public void execute4() throws Exception {
        executeAndCompare("last_name is null", HttpStatus.BAD_REQUEST);
    }

    //lastName is empty
    @Test
    public void execute5() throws Exception {
        executeAndCompare("last_name is empty", HttpStatus.BAD_REQUEST);
    }

    @Override
    protected String getTestCaseFolderPath() {
        return "person_errors/last_name/";
    }
}
