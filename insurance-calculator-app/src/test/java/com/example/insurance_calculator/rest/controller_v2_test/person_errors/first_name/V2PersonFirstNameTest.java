package com.example.insurance_calculator.rest.controller_v2_test.person_errors.first_name;

import com.example.insurance_calculator.rest.controller_v2_test.TravelCalculatePremiumControllerV2TestCase;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

public class V2PersonFirstNameTest extends TravelCalculatePremiumControllerV2TestCase {

    //firstName is empty
    @Test
    public void execute3() throws Exception {
        executeAndCompare("first_name is empty", HttpStatus.BAD_REQUEST);
    }

    //firstName is null
    @Test
    public void execute2() throws Exception {
        executeAndCompare("first_name is null", HttpStatus.BAD_REQUEST);
    }

    @Override
    protected String getTestCaseFolderPath() {
        return "person_errors/first_name/";
    }
}
