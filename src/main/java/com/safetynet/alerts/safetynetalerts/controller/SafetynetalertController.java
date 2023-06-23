package com.safetynet.alerts.safetynetalerts.controller;

import com.safetynet.alerts.safetynetalerts.entity.Person;
import com.safetynet.alerts.safetynetalerts.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
    public class SafetynetalertController {

    @Autowired
    public PersonService ps;

 /*  @GetMapping("/persons")
    public List<Person> listPerson() throws IOException {
        return  ps.getListPersons();
    }
    @GetMapping("/persons{firstName,lastName}")
    public Person showOnePersonByPhone(@PathVariable String firstName, String lastName ) throws IOException {
        return  ps.getPerson(firstName, lastName);
    }*/
}