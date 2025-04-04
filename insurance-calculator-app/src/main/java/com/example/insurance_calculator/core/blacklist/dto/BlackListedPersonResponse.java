package com.example.insurance_calculator.core.blacklist.dto;

import com.example.insurance_calculator.core.api.dto.ErrorDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BlackListedPersonResponse {
    private BlackListedPersonDTO person;
    private List<ErrorDTO> errors;
}
