package com.safetynet.alerts.safetynetalerts.repository;

import com.safetynet.alerts.safetynetalerts.entity.MedicalRecord;
import com.safetynet.alerts.safetynetalerts.entity.Person;

import java.io.IOException;
import java.util.List;

public interface MedicalrecordRepository {
    List<MedicalRecord> getMedicalRecords() throws IOException;
}
