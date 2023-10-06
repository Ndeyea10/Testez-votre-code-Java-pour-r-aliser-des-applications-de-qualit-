package com.safetynet.alerts.safetynetalerts.service;

import com.safetynet.alerts.safetynetalerts.entity.Data;
import com.safetynet.alerts.safetynetalerts.entity.Firestation;
import com.safetynet.alerts.safetynetalerts.entity.MedicalRecord;
import com.safetynet.alerts.safetynetalerts.entity.Person;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
class DataServiceTest {
    private DataService dataService = new DataService();
    @Test
    void getDataTest() throws IOException {
        final  String FILE_NAME = "src/test/resources/dataTest.json";

        Data data = dataService.getData(Paths.get(FILE_NAME));

        Firestation firestation = new Firestation();
        firestation.setAddress("1509 Culver St");
        firestation.setStation("3");

        Person person = new Person();
        person.setFirstName("John");
        person.setLastName("Boyd");
        person.setAddress("1509 Culver St");
        person.setCity("Culver");
        person.setZip("97451");
        person.setPhone("841-874-6512");
        person.setEmail("jaboyd@email.com");

        assertEquals(person, data.getPersons().get(0));
        assertEquals(firestation, data.getFirestations().get(0));
    }
    @Test
    void setDataTest() throws IOException {
        try {
        final  String FILE_NAME =   "src/test/resources/setDataTest.json";
        List<Person> persons = new ArrayList<>();
        List<MedicalRecord> medicalRecords = new ArrayList<>();
        List<Firestation> firestations = new ArrayList<>();

        Firestation firestation = new Firestation();
        firestation.setAddress("1509 Culver St");
        firestation.setStation("3");
        firestations.add(firestation);

        Person person = new Person();
        person.setFirstName("John");
        person.setLastName("Boyd");
        person.setAddress("1509 Culver St");
        person.setCity("Culver");
        person.setZip("97451");
        person.setPhone("841-874-6512");
        person.setEmail("jaboyd@email.com");
        persons.add(person);

        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setFirstName("John");
        medicalRecord.setLastName("Boyd");
        medicalRecord.setBirthdate("03/06/1984");

        Data data = new Data();
        data.setPersons(persons);
        data.setFirestations(firestations);
        data.setMedicalrecords(medicalRecords);

        dataService.setData(data, Paths.get(FILE_NAME));
        Data updateData = dataService.getData(Paths.get(FILE_NAME));

        assertEquals(firestation, updateData.getFirestations().get(0));
        assertEquals(person, updateData.getPersons().get(0));
       // assertEquals(medicalRecord, updateData.getMedicalrecords().get(0));
        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}