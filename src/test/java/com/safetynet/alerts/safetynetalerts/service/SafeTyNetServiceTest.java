package com.safetynet.alerts.safetynetalerts.service;

import com.safetynet.alerts.safetynetalerts.dto.MedicalRecordDTO;
import com.safetynet.alerts.safetynetalerts.dto.PersonByAdressDTO;
import com.safetynet.alerts.safetynetalerts.entity.Data;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class SafeTyNetServiceTest {
    @Mock private PersonService personService;
    @Mock private FirestationService firestationService;
    @Mock private MedicalrecordService medicalrecordService;
    @Test
    public void getListPhoneByStationNumberTest() throws IOException {
        SafeTyNetService safeTyNetService = new SafeTyNetService(firestationService,medicalrecordService, personService);

        List<String> firestationList1 = safeTyNetService.getListPhoneByStationNumber("2");

        assertEquals(2, firestationList1.size());
    }
    @Test
    public void getPersonInfoByFirstNameAndLastNameTest() throws IOException {
        SafeTyNetService safeTyNetService = new SafeTyNetService(firestationService,medicalrecordService, personService);

        MedicalRecordDTO medicalRecordDTO1 = safeTyNetService.getPersonInfoByFirstNameAndLastName("Jean", "Leroy");
        assertEquals(34, medicalRecordDTO1.getAge());
    }
}