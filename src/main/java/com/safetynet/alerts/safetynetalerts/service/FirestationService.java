package com.safetynet.alerts.safetynetalerts.service;

import com.safetynet.alerts.safetynetalerts.entity.Firestation;
import com.safetynet.alerts.safetynetalerts.repository.FirestationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class FirestationService implements FirestationRepository {
    private final DataService dataService;
    private final  String FILE_NAME = "src/main/java/resources/data.json";
    public FirestationService(DataService dataService) {
        this.dataService = dataService;
    }
    @Override
    public List<Firestation> getFirestations() throws IOException {
        List<Firestation> listFirestation = new ArrayList<>();
        try {
            listFirestation = dataService.getData(Paths.get(FILE_NAME)).getFirestations();
        } catch (IOException e){
            log.info(e.toString());
        }
        return listFirestation;
    }
}
