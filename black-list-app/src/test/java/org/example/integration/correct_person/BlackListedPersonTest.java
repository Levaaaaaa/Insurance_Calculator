package org.example.integration.correct_person;

import org.example.integration.AbstractBlackListAppTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

public class BlackListedPersonTest extends AbstractBlackListAppTest {
    @Override
    protected String getFolderPath() {
        return "correct-person/";
    }

    @Test
    public void execute() throws Exception {
        executeAndCompare("black-listed-person/", HttpStatus.OK);
    }
}
