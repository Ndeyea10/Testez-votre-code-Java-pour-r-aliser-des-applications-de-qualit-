package com.safetynet.alerts.safetynetalerts.service;

import com.safetynet.alerts.safetynetalerts.dto.*;
import com.safetynet.alerts.safetynetalerts.entity.Firestation;
import com.safetynet.alerts.safetynetalerts.entity.MedicalRecord;
import com.safetynet.alerts.safetynetalerts.entity.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class SafeTyNetServiceTest {
    private static SafeTyNetService safeTyNetService;
    @Mock private PersonService personService;
    @Mock  private FirestationService firestationService;
    @Mock private MedicalrecordService medicalrecordService;

    //Configuration par test
    @BeforeEach
    private void setUpPerTest() {
        try {
            safeTyNetService = new SafeTyNetService(firestationService, medicalrecordService, personService);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to set up test mock objects");
        }
    }
    @Test
    public void getListPhoneByStationNumberTest() throws IOException {
        SafeTyNetService safeTyNetService = new SafeTyNetService(firestationService,medicalrecordService, personService);

        Firestation firestation = new Firestation();
        firestation.setStation("3");
        firestation.setAddress("1509 Culver St");

        Person person = new Person();
        person.setFirstName("John");
        person.setLastName("Boyd");
        person.setAddress("1509 Culver St");
        person.setCity("Culver");
        person.setZip("97451");
        person.setPhone("841-874-6512");
        person.setEmail("jaboyd@email.com");
        List<Person> personList = new ArrayList<>();
        personList.add(person);
        List<Firestation> firestations = new ArrayList<>();
        firestations.add(firestation);

        when(firestationService.getAllFirestationByStationNumber("3")).thenReturn(firestations);
        when(personService.getListPersonByAddress("1509 Culver St")).thenReturn(personList);

        List<String> firestationList1 = safeTyNetService.getListPhoneByStationNumber("3");
        assertEquals(1, firestationList1.size());
    }
    @Test
    public void getListPersonByCityTest() throws IOException {
        SafeTyNetService safeTyNetService = new SafeTyNetService(firestationService,medicalrecordService, personService);

        Person person = new Person();
        person.setFirstName("John");
        person.setLastName("Boyd");
        person.setAddress("1509 Culver St");
        person.setCity("Culver");
        person.setZip("97451");
        person.setPhone("841-874-6512");
        person.setEmail("jaboyd@email.com");
        List<Person> personList = new ArrayList<>();
        personList.add(person);

        when(personService.getListPersonByEmail("jaboyd@email.com")).thenReturn(personList);
        when(personService.getPersonByCity("Culver")).thenReturn(personList);

        List<String> firestationList1 = safeTyNetService.getListPersonByCity("Culver");
        assertEquals(1, firestationList1.size());
    }
    @Test
    public void getPersonInfoByFirstNameAndLastNameTest() throws IOException {
        SafeTyNetService safeTyNetService = new SafeTyNetService(firestationService,medicalrecordService, personService);

        Person person = new Person();
        person.setFirstName("John");
        person.setLastName("Boyd");
        person.setAddress("1509 Culver St");
        person.setCity("Culver");
        person.setZip("97451");
        person.setPhone("841-874-6512");
        person.setEmail("jaboyd@email.com");

        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setFirstName("John");
        medicalRecord.setLastName("Boyd");
        medicalRecord.setBirthdate("03/06/1984");


        when(medicalrecordService.getMedicalRecord("John", "Boyd")).thenReturn(medicalRecord);
        when(personService.getPersonByFirstNameAndLastName("John", "Boyd")).thenReturn(person);
        when(medicalrecordService.agePerson("03/06/1984")).thenReturn(39);

       MedicalRecordDTO medicalRecordDTO1 = safeTyNetService.getPersonInfoByFirstNameAndLastName("John", "Boyd");
       assertEquals(39, medicalRecordDTO1.getAge());
       assertEquals("1509 Culver St", medicalRecordDTO1.getAddress());

    }
    @Test
    void getPersonByStationNumberTest() throws IOException, ParseException {
        SafeTyNetService safeTyNetService = new SafeTyNetService(firestationService,medicalrecordService, personService);

        Person person = new Person();
        person.setFirstName("John");
        person.setLastName("Boyd");
        person.setAddress("1509 Culver St");
        person.setPhone("841-874-6512");

        List<Person> personList = new ArrayList<>();
        personList.add(person);

        Firestation firestation = new Firestation();
        firestation.setStation("3");
        firestation.setAddress("1509 Culver St");

        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setFirstName("John");
        medicalRecord.setLastName("Boyd");
        medicalRecord.setBirthdate("03/06/1984");

        when(personService.getListPersonByAddress("1509 Culver St")).thenReturn(personList);
        when(firestationService.getFirestationByStationNumber("3")).thenReturn(firestation);
        when(medicalrecordService.getNombreDAdulte(personList)).thenReturn(3);
        when(medicalrecordService.getNombreEnfant(personList)).thenReturn(2);

        PersonByStationNumberDTO personByStationNumberDTO = safeTyNetService.getPersonByStationNumber("3");

        assertEquals(3, personByStationNumberDTO.getNbAdulte());
    }
    @Test
    void getListChildByAddressTest() throws IOException {
        SafeTyNetService safeTyNetService = new SafeTyNetService(firestationService,medicalrecordService, personService);

        Person person = new Person();
        person.setFirstName("John");
        person.setLastName("Boyd");
        person.setAddress("1509 Culver St");

        List<Person> personList = new ArrayList<>();
        personList.add(person);

        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setFirstName("John");
        medicalRecord.setLastName("Boyd");
        medicalRecord.setBirthdate("03/06/2014");

        when(personService.getListPersonByAddress("1509 Culver St")).thenReturn(personList);
        when(medicalrecordService.getMedicalRecord("John", "Boyd")).thenReturn(medicalRecord);
        when(medicalrecordService.agePerson("03/06/2014")).thenReturn(9);

        List<ChildDTO> childDTOList = safeTyNetService.getListChildByAddress("1509 Culver St");
        assertEquals(1, childDTOList.size());
    }
    @Test
    void getListPersonByAddressTest() throws IOException {
        SafeTyNetService safeTyNetService = new SafeTyNetService(firestationService,medicalrecordService, personService);

        Person person = new Person();
        person.setFirstName("John");
        person.setLastName("Boyd");
        person.setAddress("1509 Culver St");
        person.setPhone("841-874-6512");

        List<Person> personList = new ArrayList<>();
        personList.add(person);

        Firestation firestation = new Firestation();
        firestation.setStation("3");
        firestation.setAddress("1509 Culver St");

        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setFirstName("John");
        medicalRecord.setLastName("Boyd");
        medicalRecord.setBirthdate("03/06/1984");

        when(personService.getListPersonByAddress("1509 Culver St")).thenReturn(personList);
        when(firestationService.getFirestationByAddress("1509 Culver St")).thenReturn(firestation);
        when(medicalrecordService.getMedicalRecord("John", "Boyd")).thenReturn(medicalRecord);
        when(medicalrecordService.agePerson("03/06/1984")).thenReturn(39);

        List<FireDTO>  fireDTOList = safeTyNetService.getListPersonByAddress("1509 Culver St");
        assertEquals("841-874-6512", fireDTOList.get(0).getPhone());
    }
    @Test
    void getListPersonByStationNumberTest() throws IOException {
        SafeTyNetService safeTyNetService = new SafeTyNetService(firestationService,medicalrecordService, personService);

        Person person = new Person();
        person.setFirstName("John");
        person.setLastName("Boyd");
        person.setAddress("1509 Culver St");
        person.setPhone("841-874-6512");

        List<Person> personList = new ArrayList<>();
        personList.add(person);

        Firestation firestation = new Firestation();
        firestation.setStation("3");
        firestation.setAddress("1509 Culver St");

        List<Firestation> firestationList = new ArrayList<>();
        firestationList.add(firestation);

        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setFirstName("John");
        medicalRecord.setLastName("Boyd");
        medicalRecord.setBirthdate("03/06/1984");

        when(firestationService.getAllFirestationByStationNumber("3")).thenReturn(firestationList);
        when(personService.getListPersonByAddress("1509 Culver St")).thenReturn(personList);
        when(medicalrecordService.getMedicalRecord("John", "Boyd")).thenReturn(medicalRecord);
        when(medicalrecordService.agePerson("03/06/1984")).thenReturn(39);

      Map<String, List<FloodDTO>> stringListMap = safeTyNetService.getListPersonByStationNumber( List.of("3"));

       assertEquals(39, stringListMap.get("1509 Culver St").get(0).getAge());
    }
}