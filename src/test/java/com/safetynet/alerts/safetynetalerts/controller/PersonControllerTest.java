package com.safetynet.alerts.safetynetalerts.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.safetynetalerts.entity.Person;
import com.safetynet.alerts.safetynetalerts.service.PersonService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
               // .andExpect((ResultMatcher) jsonPath("firstName").value("John"))
              // .andExpect((ResultMatcher) jsonPath("lastName").value("Boyd"));
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
            person.setPhone("841-874-6512");
            person.setEmail("jaboyd@email.com");
        mockMvc.perform(post("/api/v1/person")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(person)))
                        .andExpect(status().isCreated())
                        .andDo(print());

        /*  mockMvc.perform(post("/api/v1/person")
                        .contentType("application/json"))
                .andExpect(status().isOk());*/
    }
    @Test
    public void deletePersonTest() throws Exception {
        mockMvc.perform(get("/api/v1/person/")).andExpect(status().isOk());
    }

}