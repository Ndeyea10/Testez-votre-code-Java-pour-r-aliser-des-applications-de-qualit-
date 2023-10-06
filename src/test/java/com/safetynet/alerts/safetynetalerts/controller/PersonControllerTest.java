package com.safetynet.alerts.safetynetalerts.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.safetynetalerts.entity.Person;
import com.safetynet.alerts.safetynetalerts.service.PersonService;
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
class PersonControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PersonService personService;
    @Autowired
    private ObjectMapper objectMapper;
    @Test
    public void getPersonnAllTest() throws Exception {
        mockMvc.perform(get("/api/v1/person/all")).andExpect(status().isOk());
    }
    @Test
    public void getPersonTest() throws Exception {
        mockMvc.perform(get("/api/v1/person/?firstName=John&lastName=Boyd"))
                .andExpect(status().isOk());
                //.andExpect((ResultMatcher) jsonPath("$[0].firstName", is("John"), "$[0].lastName", is("Boyd")));
    }
    @Test
    public void updatePersonTest() throws Exception {
        Person person = new Person();
        person.setFirstName("John");
        person.setLastName("Boyd");
        person.setAddress("1509 Culver St");
        person.setCity("Culver");
        person.setZip("97451");
        person.setPhone("841-874-6513");
        person.setEmail("jaboyd@email.com");

            mockMvc.perform(put("/api/v1/person/")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(person)))
                        .andExpect(status().isOk())
                        .andDo(print());
    }
    @Test
    public void deletePersonTest() throws Exception {
        mockMvc.perform(delete("/api/v1/person/?firstName=John&lastName=Boyd")).
                andExpect(status().isOk());
    }
}