package com.safetynet.alerts.safetynetalerts.service;

import com.safetynet.alerts.safetynetalerts.entity.Data;
import com.safetynet.alerts.safetynetalerts.entity.Firestation;
import com.safetynet.alerts.safetynetalerts.entity.MedicalRecord;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class MedicalrecordServiceTest {
    @Mock
    DataService dataService;
    @Test
    void getListMedicalRecordTest() throws IOException {

        MedicalrecordService medicalrecordService = new MedicalrecordService(dataService);
        List<MedicalRecord> medicalRecords = new ArrayList<>();

        MedicalRecord medicalRecord = new MedicalRecord();

        medicalRecord.setFirstName("John");
        medicalRecord.setLastName("Boyd");
        medicalRecord.setBirthdate("03/06/1984");
        medicalRecord.setMedications(new String[]{"[aznol:350mg, hydrapermazol:100mg]"});
        medicalRecord.setAllergies( new String[]{"[nillacilan]"});
        medicalRecords.add(medicalRecord);

        Data data = new Data();
        data.setMedicalrecords(medicalRecords);
        //GIVEN    simulation
        when(dataService.getData(any(Path.class))).thenReturn(data);

        List<MedicalRecord> medicalRecordList = medicalrecordService.getListMedicalRecords();
        //THEN vérification
        assertEquals(medicalRecordList, medicalRecords);
        verify(dataService, times(1)).getData(any(Path.class));
    }
}