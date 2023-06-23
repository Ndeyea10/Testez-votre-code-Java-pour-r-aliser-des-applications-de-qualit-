package com.safetynet.alerts.safetynetalerts.repository;

import com.safetynet.alerts.safetynetalerts.entity.Person;

import java.io.IOException;
import java.util.List;

public interface PersonRepository {
    List<Person> createPerson(Person person) throws IOException;
    Person getPerson(String firstName, String lastName) throws IOException;
    List<Person> getListPersons() throws IOException;
    Person updatePerson(Person person) throws IOException;
    Person deletePerson(String firstName) throws IOException;
    Person save(Person person);
}
