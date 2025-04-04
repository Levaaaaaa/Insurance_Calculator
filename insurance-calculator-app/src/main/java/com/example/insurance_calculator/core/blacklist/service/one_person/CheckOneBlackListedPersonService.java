package com.example.insurance_calculator.core.blacklist.service.one_person;

import com.example.insurance_calculator.core.api.dto.PersonDTO;
import com.example.insurance_calculator.core.blacklist.dto.BlackListedPersonResponse;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientException;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Service
public interface CheckOneBlackListedPersonService {
    public BlackListedPersonResponse check(PersonDTO personDTO) throws WebClientException;
}
