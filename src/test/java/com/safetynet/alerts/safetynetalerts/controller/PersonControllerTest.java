package com.safetynet.alerts.safetynetalerts.controller;

import com.safetynet.alerts.safetynetalerts.service.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class PersonControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PersonService personService;

    @Test
    public void getPersonnAllTest() throws Exception {
        mockMvc.perform(get("/api/v1/person/all")).andExpect(status().isOk());
    }
    @Test
    public void getPersonTest() throws Exception {
        mockMvc.perform(get("/api/v1/person/"))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$[0].firstName", is("John"), "$[0].lastName", is("Boyd")));
    }
    @Test
    public void updatePersonTest() throws Exception {
        mockMvc.perform(get("/api/v1/person/")).andExpect(status().isOk());
    }
    @Test
    public void deletePersonTest() throws Exception {
        mockMvc.perform(get("/api/v1/person/")).andExpect(status().isOk());
    }

}