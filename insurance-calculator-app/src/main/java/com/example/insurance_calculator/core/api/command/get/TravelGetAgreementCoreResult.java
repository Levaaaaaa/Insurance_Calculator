package com.example.insurance_calculator.core.api.command.get;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.example.insurance_calculator.core.api.dto.AgreementDTO;
import com.example.insurance_calculator.core.api.dto.ErrorDTO;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TravelGetAgreementCoreResult {
    private List<ErrorDTO> errors;
    private AgreementDTO agreementDTO;

    private GetResultStatus status;
    public boolean hasErrors() {
        return errors != null && !errors.isEmpty();
    }
}
