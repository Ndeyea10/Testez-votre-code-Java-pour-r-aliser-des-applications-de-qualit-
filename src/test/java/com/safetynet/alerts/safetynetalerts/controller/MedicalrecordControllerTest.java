package com.safetynet.alerts.safetynetalerts.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.safetynetalerts.entity.MedicalRecord;
import com.safetynet.alerts.safetynetalerts.entity.Person;
import com.safetynet.alerts.safetynetalerts.service.MedicalrecordService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MedicalrecordControllerTest {

    @Autowired private MockMvc mockMvc;
    @MockBean private MedicalrecordService medicalrecordService;
    @Autowired private ObjectMapper objectMapper;
    @Test
    public void getMedicalrecordAllTest() throws Exception {
        mockMvc.perform(get("/api/v1/medicalrecord/all")).andExpect(status().isOk());
    }
    @Test
    public void getMedicalrecordTest() throws Exception {
        mockMvc.perform(get("/api/v1/medicalrecord/?firstName=John&lastName=Boyd"))
                .andExpect(status().isOk());
                //.andExpect((ResultMatcher) jsonPath("$[0].firstName", is("John"), "$[0].lastName", is("Boyd")));
    }
    @Test
    public  void updateMedicalrecordTest() throws Exception {
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setFirstName("John");
        medicalRecord.setLastName("Boyd");
        medicalRecord.setBirthdate("03/06/1984");
        medicalRecord.setMedications(new String[]{"[aznol:350mg, hydrapermazol:100mg]"});
        medicalRecord.setAllergies( new String[]{"[nillacilan]"});

        mockMvc.perform(put("/api/v1/medicalrecord/")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(medicalRecord)))
                .andExpect(status().isOk())
                .andDo(print());
    }
    @Test
    public  void deleteMedicalRecordTest() throws Exception {
        mockMvc.perform(delete("/api/v1/medicalrecord/?firstName=John&lastName=Boyd")).andExpect(status().isOk());
    }
}