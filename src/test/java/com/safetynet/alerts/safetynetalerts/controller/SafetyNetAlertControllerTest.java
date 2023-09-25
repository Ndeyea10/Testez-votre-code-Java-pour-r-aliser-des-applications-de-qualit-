package com.safetynet.alerts.safetynetalerts.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SafetyNetAlertControllerTest {
    @Autowired private MockMvc mockMvc;
    @Test
    void getPersonByStation() throws Exception {

        mockMvc.perform(get("/firestation?stationNumber=3"))
                .andExpect(status().isOk());
             //   .andExpect(jsonPath("$.personDTOList[0].address", is("1509 Culver St")));
    }
    @Test
    void getAdressByCity() throws Exception {
        mockMvc.perform(get("/communityEmail?city=Culver"))
                .andExpect(status().isOk());
    }
    @Test
    void getPersonInfoByFirstNameAndLastName() throws Exception {
        mockMvc.perform(get("/personInfo?firstName=John&lastName=Boyd"))
                .andExpect(status().isOk());
    }
    @Test
    void getListChildByAddress() throws Exception {
        mockMvc.perform(get("/childAlert?address=1509 Culver St"))
                .andExpect(status().isOk());
    }
    @Test
    void getListPersonByFirestationNumber() throws Exception {
        mockMvc.perform(get("/phoneAlert?firestation=2"))
                .andExpect(status().isOk());
    }
    @Test
    void getListPersonByFirestation() throws Exception {
        mockMvc.perform(get("/fire?address=748 Townings Dr"))
                .andExpect(status().isOk());
    }
    @Test
    void getListPersonByStationNumber() throws Exception {
        mockMvc.perform(get("/flood/stations?stations=2,3"))
                .andExpect(status().isOk());
    }
}