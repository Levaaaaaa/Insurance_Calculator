package org.example.controllers;

import org.example.dto.BlackListedPersonDTO;
import org.example.services.CheckPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@RestController
@RequestMapping("/blacklist/person/check")
public class CheckPersonController {

    @Autowired
    private CheckPersonService checkPersonService;

    @GetMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> check(@RequestBody BlackListedPersonDTO person) {
        BlackListedPersonDTO result = checkPersonService.checkPerson(person);
        return ResponseEntity.ok(result);
    }
}
