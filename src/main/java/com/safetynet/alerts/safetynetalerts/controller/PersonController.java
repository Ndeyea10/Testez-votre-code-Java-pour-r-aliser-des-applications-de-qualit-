package com.safetynet.alerts.safetynetalerts.controller;

import com.safetynet.alerts.safetynetalerts.entity.Data;
import com.safetynet.alerts.safetynetalerts.entity.Person;
import com.safetynet.alerts.safetynetalerts.service.DataService;
import com.safetynet.alerts.safetynetalerts.service.PersonService;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@RestController
    public class SafetynetalertController {
    String FILE_NAME = "src/test/resources/dataTest.json";
    @Autowired
    private DataService dataService;
    @Autowired
    private PersonService ps;
   PersonService personService = new PersonService(dataService);
    public SafetynetalertController() {
    }
    @GetMapping(path = "/data", produces = MediaType.APPLICATION_JSON_VALUE)
    public Data getData (HttpServletRequest request, HttpServletResponse response) throws IOException {
        return  dataService.getData(Path.of(FILE_NAME));
    }
    @GetMapping(path = "/data/listPerson", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Person> getListPerson(HttpServletRequest request, HttpServletResponse response) throws IOException {
        return  dataService.getData(Path.of(FILE_NAME)).getPersons();
    }
    @GetMapping("/data/persons")
    public List<Person> getPerson() throws IOException {
        return  personService.getListPersons();
    }
    @GetMapping(path = "/get-person-firstName-lastName", produces = MediaType.APPLICATION_JSON_VALUE)
    public Person getPersonByFistAndLastName(@RequestParam(value = "firstName", required = false) String firstName,
                                             @RequestParam(value = "lastName", required = false)  String lastName ,
                                             HttpServletRequest request, HttpServletResponse response) throws IOException {
        //List<Person> personList = new ArrayList<>();
       return personService.getPerson(firstName, lastName);
       //return  ps.getPerson(firstName, lastName);
    }
    @PutMapping("update/person")
    public Person updatePerson(@RequestBody Person person) throws IOException {
        return personService.updatePerson(person);
        //return ps.updatePerson(person);
    }
}