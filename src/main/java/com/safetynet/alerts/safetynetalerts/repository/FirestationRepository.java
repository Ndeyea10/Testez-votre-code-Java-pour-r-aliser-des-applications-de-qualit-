package com.safetynet.alerts.safetynetalerts.repository;

import com.safetynet.alerts.safetynetalerts.entity.Firestation;

import java.io.IOException;
import java.util.List;

public interface FirestationRepository {
    List<Firestation> getFirestations() throws IOException;
}
