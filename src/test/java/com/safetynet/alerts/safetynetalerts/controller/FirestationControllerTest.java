package com.safetynet.alerts.safetynetalerts.controller;

import com.safetynet.alerts.safetynetalerts.entity.Firestation;
import com.safetynet.alerts.safetynetalerts.service.FirestationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = FirestationController.class)
class FirestationControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    FirestationService firestationService;

  /*  @Before("")
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
    }*/
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
    public  void getMer() throws Exception {
        String address = "1509 Culver St";
        mockMvc.perform(get("/api/v1/firestation/{address}",address).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) content().contentType("application/json;charset=UTF-8"))
                .andExpect((ResultMatcher) jsonPath("address").value(address));
    }
    @Test
    public void updateFirestationTest() throws Exception {
        mockMvc.perform(get("/api/v1/firestation/")).andExpect(status().isOk());
    }
}