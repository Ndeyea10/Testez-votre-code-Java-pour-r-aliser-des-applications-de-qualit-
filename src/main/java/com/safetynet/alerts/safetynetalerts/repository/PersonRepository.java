package com.safetynet.alerts.safetynetalerts.repository;

import com.safetynet.alerts.safetynetalerts.entity.Person;

import java.io.IOException;
import java.util.List;

public interface PersonRepository {
    Person getPersonByFirstNameAndLastName(String firstName, String lastName) throws IOException;
    List<Person> getListPersons() throws IOException;
    Person updatePerson(Person person) throws IOException;
    boolean deletePerson(String firstName, String lastNme) throws IOException;
}
