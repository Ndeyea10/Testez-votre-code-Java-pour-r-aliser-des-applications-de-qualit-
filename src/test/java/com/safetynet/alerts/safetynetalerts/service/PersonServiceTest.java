package com.safetynet.alerts.safetynetalerts.service;

import com.safetynet.alerts.safetynetalerts.entity.Data;
import com.safetynet.alerts.safetynetalerts.entity.Person;
import com.safetynet.alerts.safetynetalerts.repository.PersonRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class PersonServiceTest {
    @Mock
    private DataService dataService;
    @Test
    void getListPersonTest() throws IOException {
      PersonService personService = new PersonService(dataService);
        List<Person> persons = new ArrayList<>();

        Person person = new Person();
        person.setFirstName("John");
        person.setLastName("Boyd");
        person.setAddress("1509 Culver St");
        person.setCity("Culver");
        person.setZip("97451");
        person.setPhone("841-874-6512");
        person.setEmail("jaboyd@email.com");
        persons.add(person);

        Data data = new Data();
        data.setPersons(persons);
        //GIVEN    simulation
        when(dataService.getData(any(Path.class))).thenReturn(data);
        List<Person> listPerson = personService.getListPersons();
        //THEN vérification
        assertEquals(listPerson, persons);
        verify(dataService, times(1)).getData(any(Path.class));
    }
    @Test
    void  getPersonTest() throws IOException {
        PersonService  personService = new PersonService(dataService);
        List<Person> persons = new ArrayList<>();

        Person person = new Person();
        person.setFirstName("Jean");
        person.setLastName("Leroy");
        person.setEmail("jeanL@email.com");
        persons.add(person);

        Data data = new Data();
        data.setPersons(persons);

        when(dataService.getData(any(Path.class))).thenReturn(data);

        Person person1 = personService.getPerson("Jean", "Leroy");
        assertEquals("jeanL@email.com", person1.getEmail());
        verify(dataService, times(1)).getData(any(Path.class));

    }

    @Test
    void deletePersonTest() throws IOException {
        PersonService personService = new PersonService(dataService);
        List<Person> persons = new ArrayList<>();

        Person person = new Person();
        person.setFirstName("John");
        person.setLastName("Boyd");
        person.setAddress("1509 Culver St");
        person.setCity("Culver");
        person.setZip("97451");
        person.setPhone("841-874-6512");
        person.setEmail("jaboyd@email.com");
        persons.add(person);

        Data data = new Data();
        data.setPersons(persons);

        when(dataService.getData(any(Path.class))).thenReturn(data);

        boolean deletePerson = personService.deletePerson("John", "Boyd");
        assertTrue(deletePerson);
        verify(dataService, times(1)).getData(any(Path.class));

        //
    }
    @Test
    void updatePersonTest() throws IOException {
       PersonService personService = new PersonService(dataService);
        List<Person> persons = new ArrayList<>();

        Person person = new Person();
        person.setFirstName("John");
        person.setLastName("Boyd");
        person.setAddress("1509 Culver St");
        person.setCity("Culver");
        person.setZip("97451");
        person.setPhone("841-874-6512");
        person.setEmail("jaboyd@email.com");
        persons.add(person);

        Data data = new Data();
        data.setPersons(persons);

        when(dataService.getData(any(Path.class))).thenReturn(data);

        person.setZip("28888");

       Person updatePerson = personService.updatePerson(person);

       assertEquals("28888", updatePerson.getZip());
       //
    }

/*    void createPersonTest() throws IOException {
        PersonService personService = new PersonService(dataService);
        final  String FILE_NAME = "src/test/resources/dataTest.json";
        List<Person> persons = new ArrayList<>();
        Person person = new Person();
        person.setFirstName("John");
        person.setLastName("Boyd");
        person.setAddress("1509 Culver St");
        person.setCity("Culver");
        person.setZip("97451");
        person.setPhone("841-874-6512");
        person.setEmail("jaboyd@email.com");
       // persons.add(person);

    //    Person savePerson = repo.save(person);
        //  assertNotNull(savePerson);

        List<Person> listPerson = personService.createPerson(person);

        assertNotNull(listPerson);
    }*/
    /*  @Test
    void savePersonTest() throws IOException {
        List<Person> persons = new ArrayList<>();

        Person person = new Person();
        person.setAddress("1509 Culver St");
        person.setCity("Culver");
        person.setZip("97451");
        person.setPhone("841-874-6512");
        person.setEmail("jaboyd@email.com");
        persons.add(person);

        Data data = new Data();
        data.setPersons(persons);
        //GIVEN    simulation
        when(dataService.getData(any(Path.class))).thenReturn(data);

    }*/

}