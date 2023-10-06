package com.safetynet.alerts.safetynetalerts.service;

import com.safetynet.alerts.safetynetalerts.entity.Data;
import com.safetynet.alerts.safetynetalerts.entity.Firestation;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class FirestationServiceTest {
    @Mock
    DataService dataService;
    @Test
    void getListFirestationTest() throws IOException {

        FirestationService firestationService = new FirestationService(dataService);
        List<Firestation> firestations = new ArrayList<>();

        Firestation firestation = new Firestation();

        firestation.setAddress("1509 Culver St");
        firestation.setStation("3");
        firestations.add(firestation);

        Data data = new Data();
        data.setFirestations(firestations);
        //GIVEN    simulation
        when(dataService.getData(any(Path.class))).thenReturn(data);
        List<Firestation> firestationList = firestationService.getListFirestations();
        //THEN vérification
        assertEquals(firestationList, firestations);
        verify(dataService, times(1)).getData(any(Path.class));
    }

    @Test
    void  getFirestationTest() throws IOException {
        FirestationService firestationService = new FirestationService(dataService);
        List<Firestation> firestations = new ArrayList<>();

        Firestation firestation = new Firestation();

        firestation.setAddress("49100 Angers Ag");
        firestation.setStation("1");
        firestations.add(firestation);

        Data data = new Data();
        data.setFirestations(firestations);

        when(dataService.getData(any(Path.class))).thenReturn(data);

        Firestation firestation1 = firestationService.getFirestations("49100 Angers Ag");
        assertEquals("1", firestation1.getStation());
        verify(dataService, times(1)).getData(any(Path.class));
    }
    @Test
    void updateFirestationTest() throws IOException {
        FirestationService firestationService = new FirestationService(dataService);
        List<Firestation> firestations = new ArrayList<>();

        Firestation firestation = new Firestation();

        firestation.setAddress("1509 Culver St");
        firestation.setStation("3");
        firestations.add(firestation);

        Data data = new Data();
        data.setFirestations(firestations);

        when(dataService.getData(any(Path.class))).thenReturn(data);

        firestation.setStation("5");

        Firestation updateFirestation = firestationService.updateFirestation(firestation);

        assertEquals("5", updateFirestation.getStation());
    }
    @Test
    void deleteFirestationTest() throws IOException {
        FirestationService firestationService = new FirestationService(dataService);
        List<Firestation> firestations = new ArrayList<>();

        Firestation firestation = new Firestation();

        firestation.setAddress("1509 Culver St");
        firestation.setStation("3");
        firestations.add(firestation);

        Data data = new Data();
        data.setFirestations(firestations);

        when(dataService.getData(any(Path.class))).thenReturn(data);

        boolean deleteFirestation = firestationService.deleteFirestation("1509 Culver St");
        assertTrue(deleteFirestation);
        verify(dataService, times(1)).getData(any(Path.class));
    }

    /* ce test de tester que la fonction qui retourne la premiere station*/
    @Test
    public void getFirestationByStationNumberTest() throws IOException {
        FirestationService firestationService = new FirestationService(dataService);
        List<Firestation> firestations = new ArrayList<>();

        Firestation firestation = new Firestation();
        firestation.setStation("3");
        firestation.setAddress("1509 Culver St");
        firestations.add(firestation);

        Data data = new Data();
        data.setFirestations(firestations);

        when(dataService.getData(any(Path.class))).thenReturn(data);
        Firestation firestation1 = firestationService.getFirestationByStationNumber("3");
        assertEquals("1509 Culver St", firestation1.getAddress());
    }

    @Test
    public void getFirestationByAddressTest() throws IOException {
        FirestationService firestationService = new FirestationService(dataService);
        List<Firestation> firestations = new ArrayList<>();

        Firestation firestation = new Firestation();
        firestation.setStation("3");
        firestation.setAddress("1509 Culver St");
        firestations.add(firestation);

        Data data = new Data();
        data.setFirestations(firestations);

        when(dataService.getData(any(Path.class))).thenReturn(data);
        Firestation firestation1 = firestationService.getFirestationByAddress("1509 Culver St");
        assertEquals("3", firestation1.getStation());
    }
    @Test
    public void getAllFirestationByStationNumberTest() throws IOException {
        FirestationService firestationService = new FirestationService(dataService);
        List<Firestation> firestations = new ArrayList<>();

        Firestation firestation = new Firestation();
        firestation.setStation("3");
        firestation.setAddress("1509 Culver St");
        firestations.add(firestation);

        Data data = new Data();
        data.setFirestations(firestations);

        when(dataService.getData(any(Path.class))).thenReturn(data);
        List<Firestation>  firestation1 = firestationService.getAllFirestationByStationNumber("3");
        assertEquals(1, firestation1.size());
    }
}