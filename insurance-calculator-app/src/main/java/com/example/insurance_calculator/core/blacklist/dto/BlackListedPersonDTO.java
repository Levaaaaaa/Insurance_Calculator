package com.example.insurance_calculator.core.blacklist.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BlackListedPersonDTO {

    private String firstName;

    private String lastName;

    private String personCode;

    private boolean blackListed;
}
