package com.safetynet.alerts.safetynetalerts.controller;

import com.safetynet.alerts.safetynetalerts.entity.MedicalRecord;
import com.safetynet.alerts.safetynetalerts.entity.Person;
import com.safetynet.alerts.safetynetalerts.service.DataService;
import com.safetynet.alerts.safetynetalerts.service.MedicalrecordService;
import com.safetynet.alerts.safetynetalerts.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
@RestController
@RequestMapping("/api/v1/medicalrecord")
public class MedicalrecordController {
    String FILE_NAME = "src/test/resources/dataTest.json";
    @Autowired
    private MedicalrecordService medicalrecordService;

    public MedicalrecordController() {}
    @GetMapping(path = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MedicalRecord> getListMedicalRecord(HttpServletRequest request, HttpServletResponse response) throws IOException {
        return  medicalrecordService.getListMedicalRecords();
    }
    @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public MedicalRecord getMedicalRecord(@RequestParam(value = "firstName", required = false) String firstName,
                                             @RequestParam(value = "lastName", required = false)  String lastName ,
                                             HttpServletRequest request, HttpServletResponse response) throws IOException {
        return medicalrecordService.getMedicalRecord(firstName, lastName);
    }
    @PutMapping("/")
    public MedicalRecord updateMedicalRecord(@RequestBody MedicalRecord medicalRecord) throws IOException {
        return medicalrecordService.updateMedicalRecord(medicalRecord);
    }
    @DeleteMapping("/")
    public Boolean deleteMedicalRecord(@RequestParam String firstName, @RequestParam String lastName) throws IOException {
        return medicalrecordService.deleteMedicalRecord(firstName, lastName);
    }
}
