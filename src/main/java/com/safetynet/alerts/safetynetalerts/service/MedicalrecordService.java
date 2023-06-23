package com.safetynet.alerts.safetynetalerts.service;

import com.safetynet.alerts.safetynetalerts.entity.MedicalRecord;
import com.safetynet.alerts.safetynetalerts.repository.MedicalrecordRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class MedicalrecordService implements MedicalrecordRepository {
    private final DataService dataService;
    private final  String FILE_NAME = "src/main/java/resources/data.json";
    public MedicalrecordService(DataService dataService) {
        this.dataService = dataService;
    }
    @Override
    public List<MedicalRecord> getMedicalRecords() throws IOException {
        List<MedicalRecord> listMedicalRecord = new ArrayList<>();
        try {
            listMedicalRecord = dataService.getData(Paths.get(FILE_NAME)).getMedicalrecords();
        } catch (IOException e){
            log.info(e.toString());
        }
        return listMedicalRecord;
    }
}
