package com.safetynet.alerts.safetynetalerts.service;

import com.safetynet.alerts.safetynetalerts.entity.MedicalRecord;
import com.safetynet.alerts.safetynetalerts.entity.Person;
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
    private List<MedicalRecord> listMedicalRecord;
    public MedicalrecordService(DataService dataService) {
        this.dataService = dataService;
    }
    @Override
    public List<MedicalRecord> getListMedicalRecords() throws IOException {
        listMedicalRecord = dataService.getData(Paths.get(FILE_NAME)).getMedicalrecords();
        return listMedicalRecord;
    }
    /**
     * @param firstName
     * @param lastName
     * @return
     * @throws IOException
     */
    @Override
    public MedicalRecord getMedicalRecord(String firstName, String lastName) throws IOException {
        return null;
    }

    /**
     * @param medicalRecord
     * @return
     * @throws IOException
     */
    @Override
    public Person updateMedicalRecord(MedicalRecord medicalRecord) throws IOException {
        return null;
    }
    /**
     * @param firstName
     * @param lastNme
     * @return
     * @throws IOException
     */
    @Override
    public boolean deleteMedicalRecord(String firstName, String lastNme) throws IOException {
        return false;
    }
}
