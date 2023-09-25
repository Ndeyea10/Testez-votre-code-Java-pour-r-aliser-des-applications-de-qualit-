package com.safetynet.alerts.safetynetalerts.controller;

import com.safetynet.alerts.safetynetalerts.service.FirestationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
@AutoConfigureMockMvc
class FirestationControllerTest {
    @Autowired private MockMvc mockMvc;
    @MockBean private FirestationService firestationService;
    @Test
    void getListFirestation() throws Exception {
        mockMvc.perform(get("/api/v1/firestation/all")).andExpect(status().isOk());
    }
    @Test
    void getFirestation() throws Exception {
        mockMvc.perform(get("/api/v1/firestation/?address=748 Townings Dr")).andExpect(status().isOk());
    }

    @Test
    void updateFirestation() throws Exception {
        mockMvc.perform(put("/api/v1/firestation")).andExpect(status().isOk());
    }
    @Test
    void deleteFirestation() throws Exception {
        mockMvc.perform(get("/api/v1/firestation/")).andExpect(status().isOk());
    }
}