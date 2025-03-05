package org.example.services;

import org.example.dto.BlackListedPersonDTO;
import org.springframework.stereotype.Service;

@Service
public interface CheckPersonService {
    public BlackListedPersonDTO checkPerson(BlackListedPersonDTO person);
}
