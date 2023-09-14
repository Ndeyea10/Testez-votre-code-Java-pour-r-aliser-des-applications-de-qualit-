package com.safetynet.alerts.safetynetalerts.controller;

import com.safetynet.alerts.safetynetalerts.dto.*;
import com.safetynet.alerts.safetynetalerts.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@RestController
public class SafetyNetAlertController {
    String FILE_NAME = "src/test/resources/dataTest.json";
    @Autowired
   private SafeTyNetService safeTyNetService;

  @GetMapping(path = "/firestation", produces = MediaType.APPLICATION_JSON_VALUE)
  public PersonByStationNumberDTO getPersonByStation(@RequestParam  String stationNumber) throws IOException, ParseException {
                  return safeTyNetService.getPersonByStationNumber(stationNumber);
    }
  @GetMapping(path = "/communityEmail", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PersonByAdressDTO>  getAdressByCity(@RequestParam String city) throws IOException {
        return  safeTyNetService.getListPersonByCity(city);
  }
  @GetMapping(path = "/personInfo", produces = MediaType.APPLICATION_JSON_VALUE)
    public MedicalRecordDTO getPersonInfoByFirstNameAndLastName(@RequestParam String firstName, @RequestParam String lastName) throws IOException {
      return safeTyNetService.getPersonInfoByFirstNameAndLastName(firstName, lastName);
  }
  @GetMapping(path = "/childAlert", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<ChildDTO> getListChildByAddress(@RequestParam String address) throws IOException {
      return safeTyNetService.getListChildByAddress(address);
  }
  @GetMapping(path = "/phoneAlert", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<String>  getListPersonByFirestationNumber(@RequestParam String firestationNumber) throws IOException {
      return  safeTyNetService.getListPhoneByStationNumber(firestationNumber);
  }
  @GetMapping(path = "/fire", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<FireDTO>  getListPersonByFirestation(@RequestParam String address) throws IOException {
      return  safeTyNetService.getListPersonByAddress(address);
  }
  @GetMapping(path = "/flood/stations", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<FireDTO> getListPersonByStationNumber(@RequestParam List<String>  stations) throws IOException {
      return  safeTyNetService.getListPersonByStationNumber(stations);
  }



}
