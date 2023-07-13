package com.safetynet.alerts.safetynetalerts.repository;

import com.safetynet.alerts.safetynetalerts.entity.Firestation;
import com.safetynet.alerts.safetynetalerts.entity.Person;

import java.io.IOException;
import java.util.List;

public interface FirestationRepository {
    Firestation getFirestations(String address) throws IOException;
    List<Firestation> getListFirestations() throws IOException;
    Firestation updateFirestation(Firestation firestation) throws IOException;
    boolean deleteFirestation(String address) throws IOException;
}


