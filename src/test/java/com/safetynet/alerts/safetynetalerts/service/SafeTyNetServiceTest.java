package com.safetynet.alerts.safetynetalerts.service;
import com.safetynet.alerts.safetynetalerts.dto.MedicalRecordDTO;
import com.safetynet.alerts.safetynetalerts.entity.Firestation;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SafeTyNetServiceTest {
    @Mock
    private DataService dataService;
    @Test
    void getPersonByStationTest() throws IOException {
        /*SafeTyNetService safeTyNetService = new SafeTyNetService();

        Firestation firestation1 = firestationService.getFirestationByStationNumber("3");
        List<Person> personList = personService.getListPersonByAddress(firestation1.getAddress());
        assertEquals("1509 Culver St", personList.get(1).getAddress());
        verify(dataService, times(1)).getData(any(Path.class));
*/
    }

    @Test
    public void getPersonInfoByFirstNameAndLastName() throws IOException {
        SafeTyNetService safeTyNetService = new SafeTyNetService();
        MedicalRecordDTO medicalRecordDTO1 = safeTyNetService.getPersonInfoByFirstNameAndLastName("Jean", "Leroy");
        assertEquals(34, medicalRecordDTO1.getAge());
    }

    @Test
    public void getListPhoneByStationNumber() throws IOException {
        SafeTyNetService safeTyNetService = new SafeTyNetService();
        List<String> firestationList1 = safeTyNetService.getListPhoneByStationNumber("3");
        assertEquals(4, firestationList1.size());
    }

}