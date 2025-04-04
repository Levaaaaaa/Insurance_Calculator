package org.example.integration;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;

import java.io.IOException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static uk.org.webcompere.modelassert.json.JsonAssertions.assertJson;

@SpringBootTest
@AutoConfigureMockMvc
public abstract class AbstractBlackListAppTest {
    //get test request and response
    //get app response
    //compare
    @Autowired
    private MockMvc mockMvc;

    private static final String BASE_URL = "/blacklist/person/check";
    private static JsonFileReader jsonFileReader = new JsonFileReader();

    protected abstract String getFolderPath();

    protected void executeAndCompare(String testCaseFolderName, HttpStatus expectedStatus) throws Exception{
        executeAndCompare(
                "rest/" + getFolderPath() + testCaseFolderName + "request.json",
                "rest/" + getFolderPath() + testCaseFolderName + "response.json",
                expectedStatus
        );
    }

    private void executeAndCompare(String requestPath, String responsePath, HttpStatus expectedStatus) throws Exception {
        String request = jsonFileReader.readJsonFromFile(requestPath);
        String expectedResponse = jsonFileReader.readJsonFromFile(responsePath);

        ResultMatcher resultMatcher = switch (expectedStatus)
        {
            case BAD_REQUEST: yield status().isBadRequest();
            default: yield status().isOk();
        };
        MvcResult result = mockMvc.perform(post(BASE_URL).content(request).header(
                HttpHeaders.CONTENT_TYPE,
                MediaType.APPLICATION_JSON_VALUE
        )).andExpect(resultMatcher).andReturn();

        String responseBody = result.getResponse().getContentAsString();
        assertJson(responseBody).where()
                .keysInAnyOrder()
                .arrayInAnyOrder()
                .isEqualTo(expectedResponse);
    }
}
