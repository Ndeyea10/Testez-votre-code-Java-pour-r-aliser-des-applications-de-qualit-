package com.safetynet.alerts.safetynetalerts.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.safetynetalerts.entity.Firestation;
import com.safetynet.alerts.safetynetalerts.entity.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
@AutoConfigureMockMvc
class FirestationControllerTest {
    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;
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

        Firestation firestation = new Firestation();
        firestation.setStation("3");
        firestation.setAddress("1509 Culver St");

        mockMvc.perform(put("/api/v1/firestation/")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(firestation)))
                .andExpect(status().isOk())
                .andDo(print());
    }
    @Test
    void deleteFirestation() throws Exception {
      mockMvc.perform(delete("/api/v1/firestation/?address=748 Townings Dr")).andExpect(status().isOk());
    }
}