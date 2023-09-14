package com.safetynet.alerts.safetynetalerts.service;

import com.safetynet.alerts.safetynetalerts.entity.Data;
import com.safetynet.alerts.safetynetalerts.entity.Firestation;
import com.safetynet.alerts.safetynetalerts.entity.MedicalRecord;
import com.safetynet.alerts.safetynetalerts.entity.Person;
import com.safetynet.alerts.safetynetalerts.repository.FirestationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class FirestationService implements FirestationRepository {

    private final DataService dataService;
    private final  String FILE_NAME = "src/main/resources/data.json";
    private List<Firestation> listFirestation;

    public FirestationService(DataService dataService) {
        this.dataService = dataService;
    }

    @Override
    public List<Firestation> getListFirestations() throws IOException {
    //    List<Firestation> listFirestation = new ArrayList<>();

            listFirestation = dataService.getData(Paths.get(FILE_NAME)).getFirestations();

        return listFirestation;
    }

    /**
     * @param address
     * @return
     * @throws IOException
     */
    @Override
    public Firestation getFirestations(String address) throws IOException {
        List<Firestation> firestationList = getListFirestations();
        Optional<Firestation> optionalFirestation = firestationList.stream()
                .filter(firestation -> firestation.getAddress().equals(address))
                .findFirst();
        if (optionalFirestation.isPresent()){
            return optionalFirestation.get();
        }
        return  null;
    }

    /**
     * @return
     * @throws IOException
     */


    /**
     * @param firestation
     * @return
     * @throws IOException
     */
    @Override
    public Firestation updateFirestation(Firestation firestation) throws IOException {
        int cpt = 0;
        listFirestation  = getListFirestations();
        String address = null;
        for (int i = 0; i < listFirestation.size(); i++) {
            if (listFirestation.get(i).getAddress() == (firestation.getAddress())) {
                address = firestation.getAddress();
                cpt = i;
                break;
            }
        }

        Firestation firestation1 = new Firestation();
        firestation1.setAddress(address);
        firestation1.setStation(firestation.getStation());

        listFirestation.set(cpt, firestation);
        Data data =  buildData( dataService.getData(Paths.get(FILE_NAME)).getPersons(), dataService.getData(Paths.get(FILE_NAME)).getMedicalrecords(),listFirestation);
        dataService.setData(data,Paths.get(FILE_NAME));
        return firestation1;
    }

    /**
     * @param address
     * @return
     * @throws IOException
     */
    @Override
    public boolean deleteFirestation(String address) throws IOException {
            List<Firestation> firestationList = getListFirestations();
            firestationList.removeIf(firestation -> firestation.getAddress() == (address));
            // System.out.println("firestation deleted.");
            return true;
    }

    private Data buildData(List<Person> persons, List<MedicalRecord> medicalrecords, List<Firestation> firestations){

        Data data = new Data();
        data.setPersons(persons);
        data.setMedicalrecords(medicalrecords);
        data.setFirestations(firestations);
        return data;
    }
    // Ctte fonction ne retourne que la premier station
    public Firestation getFirestationByStationNumber(String stationNumber) throws IOException {
        List<Firestation> firestationList = getListFirestations();
        Optional<Firestation> optionalFirestation = firestationList.stream().filter(firestation -> firestation.getStation().equals(stationNumber)).
                findFirst();
        if (optionalFirestation.isPresent()){
            return optionalFirestation.get();
        }
        return  null;
    }
    public   List<Firestation>  getAllFirestationByStationNumber(String stationNumber) throws IOException {
        List<Firestation> firestationList = getListFirestations();
        return firestationList.stream().filter(firestation -> firestation.getStation().equals(stationNumber)).toList();
    }
    public Firestation getFirestationByAddress(String address) throws IOException {
        List<Firestation> firestationList = getListFirestations();
        Optional<Firestation> optionalFirestation = firestationList.stream().filter(firestation -> firestation.getAddress().equals(address)).
                findFirst();
        if (optionalFirestation.isPresent()){
            return optionalFirestation.get();
        }
        return  null;
    }
}

