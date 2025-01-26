package com.example.insurance_calculator.rest.controller_v2_test.agreement_errors.selected_risks;

import com.example.insurance_calculator.rest.controller_v2_test.TravelCalculatePremiumControllerV2TestCase;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

public class V2AgreementSelectedRisksTest extends TravelCalculatePremiumControllerV2TestCase {

    //Field selectedRisks is null
    @Test
    public void execute13() throws Exception {
        executeAndCompare("selected_risks is null", HttpStatus.BAD_REQUEST);
    }

    //Field selectedRisks is empty
    @Test
    public void execute14() throws Exception {
        executeAndCompare("selected_risks is empty", HttpStatus.BAD_REQUEST);
    }

    //one risk is not supported
    @Test
    public void execute15() throws Exception {
        executeAndCompare("one not supported risk", HttpStatus.BAD_REQUEST);
    }

    //two risks are not supported
    @Test
    public void execute16() throws Exception {
        executeAndCompare("two not supported risks", HttpStatus.BAD_REQUEST);
    }

    @Override
    protected String getTestCaseFolderPath() {
        return "agreement_error/selected_risks/";
    }
}
