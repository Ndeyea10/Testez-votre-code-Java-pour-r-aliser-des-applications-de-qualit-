package com.safetynet.alerts.safetynetalerts.controller;

import com.safetynet.alerts.safetynetalerts.entity.Firestation;
import com.safetynet.alerts.safetynetalerts.service.FirestationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.swing.*;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = FirestationController.class)
class FirestationControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    FirestationService firestationService;
    @Test
    public void getFirestationAllTest() throws Exception {
        mockMvc.perform(get("/api/v1/firestation/all")).andExpect(status().isOk());
    }
    @Test
    public void getFirestationTest() throws Exception {
        //GIVEN
        Firestation firestation =new Firestation();
       /* firestation.setAddress("1509 Culver St");
        firestation.setStation("3");*/

        //WHEN
        mockMvc.perform(get("/api/v1/firestation/?address=1509 Culver St"))
                .andExpect(status().isOk());
                //.andExpect(MockMvcResultMatchers.jsonPath("$.address").value("1509 Culver St"));
            //    .andExpect(jsonPath("$[0].address", is("1509 Culver St")));
    }
    @Test
    public void updateFirestationTest() throws Exception {
        mockMvc.perform(get("/api/v1/firestation/")).andExpect(status().isOk());
    }
}