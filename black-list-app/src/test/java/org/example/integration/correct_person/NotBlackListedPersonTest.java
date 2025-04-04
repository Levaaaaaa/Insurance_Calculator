package org.example.integration.correct_person;

import org.example.integration.AbstractBlackListAppTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;


public class NotBlackListedPersonTest extends AbstractBlackListAppTest {
    @Override
    protected String getFolderPath() {
        return "correct-person/";
    }

    @Test
    public void execute() throws Exception {
        executeAndCompare("not-black-listed-person/", HttpStatus.OK);
    }
}
