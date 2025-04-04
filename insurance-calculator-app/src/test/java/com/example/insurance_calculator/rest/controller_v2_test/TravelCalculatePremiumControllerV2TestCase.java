package com.example.insurance_calculator.rest.controller_v2_test;

import com.example.insurance_calculator.rest.JsonFileReader;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;

import static com.example.insurance_calculator.rest.RemoveRandomValues.removeRandomValues;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static uk.org.webcompere.modelassert.json.JsonAssertions.assertJson;
import static uk.org.webcompere.modelassert.json.JsonAssertions.jsonFile;

@SpringBootTest
@AutoConfigureWireMock(port = 8082)
@AutoConfigureMockMvc
public abstract class TravelCalculatePremiumControllerV2TestCase {
    @Autowired
    private MockMvc mockMvc;

    @Autowired private JsonFileReader jsonFileReader;

    private static final String BASE_URL = "/insurance/travel/api/v2/";

    protected abstract String getTestCaseFolderPath();

    protected void executeAndCompare(String testCaseFolderName, HttpStatus expectedStatus) throws Exception{
        executeAndCompare(
                "rest/v2/" + getTestCaseFolderPath() + testCaseFolderName + "/request.json",
                "rest/v2/" + getTestCaseFolderPath() + testCaseFolderName + "/response.json",
                "rest/v2/black_listed/response.json",
                expectedStatus
        );
    }

    protected void executeAndCompare(String jsonRequestFilePath,
                                     String jsonResponseFilePath,
                                     String blackListedResponsePath,
                                     HttpStatus expectedStatus) throws Exception {
        String jsonRequest = jsonFileReader.readJsonFromFile(jsonRequestFilePath);

        ResultMatcher matcher = switch (expectedStatus) {
            case BAD_REQUEST: yield status().isBadRequest();
            default: yield status().isOk();
        };

        String blackListedResponse = jsonFileReader.readJsonFromFile(blackListedResponsePath);
        WireMock.stubFor(post(urlEqualTo("/blacklist/person/check")).willReturn(
                aResponse().withHeader("Content-Type", "application/json")
                        .withBody(blackListedResponse)
                        .withStatus(200)
                        )
        );
        MvcResult result = mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders
                        .post(BASE_URL)
                        .content(jsonRequest)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(matcher)
                .andReturn();



        String responseBodyContent = removeRandomValues(result.getResponse().getContentAsString());

        String jsonResponse = jsonFileReader.readJsonFromFile(jsonResponseFilePath);


        assertJson(responseBodyContent)
                .where()
                .keysInAnyOrder()
                .arrayInAnyOrder()
                .isEqualTo(jsonResponse);
    }
}
