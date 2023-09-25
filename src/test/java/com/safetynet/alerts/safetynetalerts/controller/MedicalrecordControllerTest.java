package com.safetynet.alerts.safetynetalerts.controller;

import com.safetynet.alerts.safetynetalerts.service.MedicalrecordService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import javax.swing.plaf.PanelUI;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MedicalrecordControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MedicalrecordService medicalrecordService;
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
        mockMvc.perform(put("/api/v1/medicalrecord/")).andExpect(status().isOk());
    }
    @Test
    public  void deleteMedicalRecordTest() throws Exception {
        mockMvc.perform(get("/api/v1/medicalrecord/")).andExpect(status().isOk());
    }
}