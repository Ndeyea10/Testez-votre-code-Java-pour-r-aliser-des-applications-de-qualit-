package com.safetynet.alerts.safetynetalerts.service;

import com.safetynet.alerts.safetynetalerts.dto.PersonDTO;
import com.safetynet.alerts.safetynetalerts.entity.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PersonDTOService {
    @Autowired
    private PersonService personService;
    public PersonDTOService(PersonService personService) {
        this.personService = personService;
    }


}
