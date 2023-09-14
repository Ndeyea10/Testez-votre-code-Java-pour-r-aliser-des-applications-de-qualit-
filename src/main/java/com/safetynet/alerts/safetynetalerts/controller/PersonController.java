package com.safetynet.alerts.safetynetalerts.controller;

import com.safetynet.alerts.safetynetalerts.entity.Data;
import com.safetynet.alerts.safetynetalerts.entity.Person;
import com.safetynet.alerts.safetynetalerts.service.DataService;
import com.safetynet.alerts.safetynetalerts.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

@RestController
@RequestMapping("/api/v1/person")
    public class PersonController {
    String FILE_NAME = "src/test/resources/dataTest.json";
    @Autowired
    private PersonService ps;
    @Autowired
    private DataService dataService;
    public PersonController() {
    }
   /*@GetMapping(path = "/data", produces = MediaType.APPLICATION_JSON_VALUE)
    public Data getData (HttpServletRequest request, HttpServletResponse response) throws IOException {
        return  dataService.getData(Path.of(FILE_NAME));
    }*/
    @GetMapping(path = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Person> getListPerson(HttpServletRequest request, HttpServletResponse response) throws IOException {
        return  ps.getListPersons();
    }
    @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public Person getPersonByFistAndLastName(@RequestParam(value = "firstName", required = false) String firstName,
                                             @RequestParam(value = "lastName", required = false)  String lastName ,
                                             HttpServletRequest request, HttpServletResponse response) throws IOException {
       return ps.getPerson(firstName, lastName);
    }
    @PutMapping("/")
    public Person updatePerson(@RequestBody Person person) throws IOException {
        return ps.updatePerson(person);
        //return ps.updatePerson(person);
    }
    @DeleteMapping("/")
    public Boolean deletePerson(@PathVariable String firstName, @PathVariable String lastName) throws IOException {
        return ps.deletePerson(firstName, lastName);

    }
}