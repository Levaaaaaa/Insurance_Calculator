package com.example.insurance_calculator.core.services.get;

import com.example.insurance_calculator.core.api.command.get.GetResultStatus;
import com.example.insurance_calculator.core.api.command.get.TravelGetAgreementCoreCommand;
import com.example.insurance_calculator.core.api.command.get.TravelGetAgreementCoreResult;
import com.example.insurance_calculator.core.api.dto.ErrorDTO;
import com.example.insurance_calculator.persistence.entities.agreement.AgreementEntity;
import com.example.insurance_calculator.persistence.repositories.get.GetAgreementRepository;
import com.example.insurance_calculator.core.validations.get.GetCommandUUIDValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class GetAgreementServiceImpl implements GetAgreementService{
    @Autowired
    private GetCommandUUIDValidator validator;

    @Autowired
    private GetAgreementRepository getAgreementRepository;

    @Autowired
    private BuildAgreementService buildAgreementService;


    @Override
    public TravelGetAgreementCoreResult getAgreement(TravelGetAgreementCoreCommand command) {
        List<ErrorDTO> errors = validator.validate(command.getUuid());
        return errors.isEmpty()
                ? buildCorrectResult(command.getUuid())
                : buildResultWithErrors(errors);
    }

    private TravelGetAgreementCoreResult buildResultWithErrors(List<ErrorDTO> errors) {
        TravelGetAgreementCoreResult result = new TravelGetAgreementCoreResult();
        result.setStatus(GetResultStatus.COMPLETED_WITH_ERRORS);
        result.setErrors(errors);
        return result;
    }

    private TravelGetAgreementCoreResult buildCorrectResult(String uuid) {
        TravelGetAgreementCoreResult result = new TravelGetAgreementCoreResult();
        Optional<AgreementEntity> optionalAgreementDomain = getAgreementRepository.findByUuid(UUID.fromString(uuid));
        if (optionalAgreementDomain.isEmpty()) {
            result.setStatus(GetResultStatus.NOT_FOUND_UUID);
            return result;
        }
        result.setStatus(GetResultStatus.SUCCESS);
        try {
            result.setAgreementDTO(buildAgreementService.buildAgreement(optionalAgreementDomain.get()));
        }
        catch (IllegalArgumentException e) {

        }
        return result;
    }



}
