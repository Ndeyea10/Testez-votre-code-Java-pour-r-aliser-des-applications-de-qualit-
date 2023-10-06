package com.safetynet.alerts.safetynetalerts.service;

import com.safetynet.alerts.safetynetalerts.entity.Data;
import com.safetynet.alerts.safetynetalerts.entity.Firestation;
import com.safetynet.alerts.safetynetalerts.entity.Person;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
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

        Person person1 = personService.getPersonByFirstNameAndLastName("Jean", "Leroy");
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

    }

    @Test
    public void getFirestationByAddressTest() throws IOException {
        PersonService  personService = new PersonService(dataService);
        List<Person> persons = new ArrayList<>();

        Person person = new Person();
        person.setFirstName("Jean");
        person.setLastName("Leroy");
        person.setEmail("jeanL@email.com");
        person.setAddress("1509 Culver St");
        persons.add(person);

        Data data = new Data();
        data.setPersons(persons);

        when(dataService.getData(any(Path.class))).thenReturn(data);
        List<Person> personList = personService.getListPersonByAddress("1509 Culver St");
        assertEquals("jeanL@email.com", personList.get(0).getEmail());
    }
    @Test
    public void getPersonByCityTest() throws IOException {
        PersonService  personService = new PersonService(dataService);
        List<Person> persons = new ArrayList<>();

        Person person = new Person();
        person.setCity("Culver");
        person.setEmail("jaboyd@email.com");
        persons.add(person);

        Data data = new Data();
        data.setPersons(persons);

        when(dataService.getData(any(Path.class))).thenReturn(data);
        List<Person> person1 = personService.getPersonByCity("Culver");
        assertEquals("jaboyd@email.com", person1.get(0).getEmail());

    }

    @Test
    public void getListPersonByEmailTest() throws IOException {
        PersonService  personService = new PersonService(dataService);
        List<Person> persons = new ArrayList<>();

        Person person = new Person();
        person.setCity("Culver");
        person.setEmail("jaboyd@email.com");
        persons.add(person);

        Data data = new Data();
        data.setPersons(persons);

        when(dataService.getData(any(Path.class))).thenReturn(data);
        List<Person> personList = personService.getListPersonByEmail("jaboyd@email.com");
        assertEquals(1, personList.size());
    }
    @Test
    void createPersonTest() throws IOException {
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
        persons.add(person);

        List<Person> listPerson = personService.createPerson(person);
        assertNotNull(listPerson);
    }

    @Test
    public void getPersonByCitiesTest() throws IOException {
        PersonService personService = new PersonService(dataService);
        List<Person> persons = new ArrayList<>();

        Person person = new Person();
        person.setCity("Culver");
        person.setEmail("jaboyd@email.com");
        persons.add(person);

        Data data = new Data();
        data.setPersons(persons);

        when(dataService.getData(any(Path.class))).thenReturn(data);
        Person person1 = personService.getPersonByCities("Culver");
        assertEquals("jaboyd@email.com", person1.getEmail());
    }
    /*public void buildDataTest(){
        PersonService personService = new PersonService(dataService);

        FirestationService firestationService = new FirestationService(dataService);
        List<Firestation> firestations = new ArrayList<>();

        Firestation firestation = new Firestation();
        firestation.setStation("3");
        firestation.setAddress("1509 Culver St");
        firestations.add(firestation);

        Data data = new Data();
        data.setFirestations(firestations);

    }*/
}