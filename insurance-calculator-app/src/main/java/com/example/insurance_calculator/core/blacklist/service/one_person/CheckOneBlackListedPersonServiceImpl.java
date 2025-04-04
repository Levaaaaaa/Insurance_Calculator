package com.example.insurance_calculator.core.blacklist.service.one_person;

import com.example.insurance_calculator.core.api.dto.ErrorDTO;
import com.example.insurance_calculator.core.blacklist.dto.BlackListedPersonDTO;
import com.example.insurance_calculator.core.api.dto.PersonDTO;
import com.example.insurance_calculator.core.blacklist.dto.BlackListedPersonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import javax.naming.ServiceUnavailableException;
import java.util.List;

@Service
class CheckOneBlackListedPersonServiceImpl implements CheckOneBlackListedPersonService {

//    @Value("blacklist.app.url")
//    private String blackListedAppURL;

    @Autowired
    private WebClient webClient;

    @Override
    public BlackListedPersonResponse check(PersonDTO personDTO) throws WebClientException {
        BlackListedPersonDTO blackListedPerson = BlackListedPersonDTO.builder()
                .firstName(personDTO.getPersonFirstName())
                .lastName(personDTO.getPersonLastName())
                .personCode(personDTO.getPersonIc().toString())
                .build();

        WebClient.ResponseSpec spec = webClient.post().uri("")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(blackListedPerson)
                .retrieve();
        BlackListedPersonDTO response = spec
                .bodyToMono(BlackListedPersonDTO.class)
                .block();
        return new BlackListedPersonResponse(response, List.of());
        //todo add catch validation error

    }
}
