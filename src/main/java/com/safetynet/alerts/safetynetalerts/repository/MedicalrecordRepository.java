package com.safetynet.alerts.safetynetalerts.repository;

import com.safetynet.alerts.safetynetalerts.entity.MedicalRecord;

import java.io.IOException;
import java.util.List;

public interface MedicalrecordRepository {
    List<MedicalRecord> getListMedicalRecords() throws IOException;
    MedicalRecord getMedicalRecord(String firstName, String lastName) throws IOException;
    MedicalRecord updateMedicalRecord(MedicalRecord medicalRecord) throws IOException;
    boolean deleteMedicalRecord(String firstName, String lastNme) throws IOException;
}
