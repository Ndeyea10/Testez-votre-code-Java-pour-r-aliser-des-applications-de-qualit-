package com.safetynet.alerts.safetynetalerts.controller;

import com.safetynet.alerts.safetynetalerts.entity.Firestation;
import com.safetynet.alerts.safetynetalerts.service.FirestationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/firestation")
public class FirestationController {
    String FILE_NAME = "src/test/resources/dataTest.json";
    @Autowired
    private FirestationService firestationService;

    public FirestationController() {}
    @GetMapping(path = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Firestation> getListFirestation(HttpServletRequest request, HttpServletResponse response) throws IOException {
        return firestationService.getListFirestations();
    }
    @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public Firestation getFirestation(@RequestParam(value = "address") String address,
                                      HttpServletRequest request, HttpServletResponse response) throws IOException {
        return firestationService.getFirestations(address);
    }
    @PutMapping(path="/", produces = MediaType.APPLICATION_JSON_VALUE)
    public Firestation updateFirestation(@RequestBody Firestation firestation) throws IOException {
        return firestationService.updateFirestation(firestation);
    }
    @DeleteMapping(path="/",produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean deleteFirestation(@RequestParam String address) throws IOException {
        return firestationService.deleteFirestation(address);
    }
}