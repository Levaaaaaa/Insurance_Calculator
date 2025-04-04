package org.example.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BlackListedPersonDTO {

    @NotBlank(message = "ERROR_CODE_1")
    private String firstName;

    @NotBlank(message = "ERROR_CODE_2")
    private String lastName;

    @NotBlank(message = "ERROR_CODE_3")
    @Pattern(regexp = "/[0-9]{8}-[0-9]{4}-[0-9]{4}-[0-9]{4}-[0-9]{12}", message = "ERROR_CODE_4")
    private String personCode;

    private boolean blackListed;
}
