package com.safetynet.alerts.safetynetalerts.repository;

import com.safetynet.alerts.safetynetalerts.entity.MedicalRecord;
import com.safetynet.alerts.safetynetalerts.entity.Person;

import java.io.IOException;
import java.util.List;

public interface MedicalrecordRepository {
    List<MedicalRecord> getListMedicalRecords() throws IOException;
    MedicalRecord getMedicalRecord(String firstName, String lastName) throws IOException;
    Person updateMedicalRecord(MedicalRecord medicalRecord) throws IOException;
    boolean deleteMedicalRecord(String firstName, String lastNme) throws IOException;
}
