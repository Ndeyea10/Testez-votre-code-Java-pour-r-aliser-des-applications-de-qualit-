package com.safetynet.alerts.safetynetalerts.service;

import com.safetynet.alerts.safetynetalerts.dto.*;
import com.safetynet.alerts.safetynetalerts.entity.Firestation;
import com.safetynet.alerts.safetynetalerts.entity.MedicalRecord;
import com.safetynet.alerts.safetynetalerts.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
@Service
public class SafeTyNetService {
    @Autowired
    private FirestationService firestationService;
    @Autowired
    private MedicalrecordService medicalrecordService;
    @Autowired
    private PersonService personService;
    @Autowired
    private PersonDTOService personDTOService;

    public PersonByStationNumberDTO getPersonByStationNumber(String stationNumber) throws IOException, ParseException {
        Firestation firestation = firestationService.getFirestationByStationNumber(stationNumber);
        List<Person> personList = personService.getListPersonByAddress(firestation.getAddress());
        PersonByStationNumberDTO personByStationNumberDTO = new PersonByStationNumberDTO();
        personByStationNumberDTO.setNbAdulte(medicalrecordService.getNombreDAdulte(personList));
        personByStationNumberDTO.setNbEnfant(medicalrecordService.getNombreEnfant(personList));
        List<PersonDTO> personDTOList = personList.stream().map(person -> new PersonDTO(person.getFirstName(), person.getLastName(), person.getAddress(), person.getPhone())).toList();
        personByStationNumberDTO.setPersonDTOList(personDTOList);

        return personByStationNumberDTO;
    }

    public List<PersonByAdressDTO> getListPersonByCity(String city) throws IOException {
        Person person = personService.getPersonByEmailByCity(city);
        List<Person> personList = personService.getListPersonByEmail(person.getEmail());
        PersonByAdressDTO personByAdressDTO = new PersonByAdressDTO(person.getEmail());
        //personList.stream().map(person1 -> new PersonByAdressDTO(person1.getEmail()));

        Person person1 = new Person();
        List<PersonByAdressDTO> personDTOList = personList.stream().map(per -> new PersonByAdressDTO(per.getEmail())).toList();
        person1.setEmail(personDTOList.toString());
        return personDTOList;
    }
    public MedicalRecordDTO getPersonInfoByFirstNameAndLastName(String firstName, String lastaName) throws IOException {
        Person person = personService.getPerson(firstName, lastaName);
        MedicalRecord medicalRecord = medicalrecordService.getMedicalRecord(firstName, lastaName);

        int agePerson = medicalrecordService.agePerson(medicalRecord.getBirthdate());
        MedicalRecordDTO medicalRecordDTO = new MedicalRecordDTO();
        medicalRecordDTO.setLastName(person.getLastName());
        medicalRecordDTO.setFirstName(person.getFirstName());
        medicalRecordDTO.setEmail(person.getEmail());
        medicalRecordDTO.setAddress(person.getAddress());
        medicalRecordDTO.setMedications(medicalRecordDTO.getMedications());
        medicalRecordDTO.setAllergies(medicalRecordDTO.getAllergies());
        medicalRecordDTO.setAge(agePerson);

        return medicalRecordDTO;
    }
    public List<ChildDTO> getListChildByAddress(String address) throws IOException {
        List<Person> personList = personService.getListPersonByAddress(address);
        int ageP = 0;
        List<ChildDTO> childDTOList = new ArrayList<>();
        for (Person person : personList) {
            MedicalRecord medicalRecord = medicalrecordService.getMedicalRecord(person.getFirstName(), person.getLastName());
            int age = medicalrecordService.agePerson(medicalRecord.getBirthdate());
            ageP = age;
            if (age <= 18) {
                return personList.stream().map(per -> new ChildDTO(per.getFirstName(), per.getLastName(), age)).toList();
            }
        }
        return null;
    }
    public List<String> getListPhoneByStationNumber(String firestationNumber) throws IOException {
        List<String> phones = new ArrayList<>();
        List<Firestation> firestations = firestationService.getAllFirestationByStationNumber(firestationNumber);
        for (int i = 0; i < firestations.size(); i++) {
            List<Person> personList = personService.getListPersonByAddress(firestations.get(i).getAddress());
            phones.addAll(personList.stream().map(person -> (person.getPhone())).toList());
        }
        return phones;
    }

    public List<FireDTO> getListPersonByAddress(String address) throws IOException {
        Firestation firestation = firestationService.getFirestationByAddress(address);
        List<Person> personList = personService.getListPersonByAddress(firestation.getAddress());

        List<FireDTO> fireDTOList = personList.stream()
                .map(person -> {
                            try {
                                MedicalRecord medicalRecord = medicalrecordService.getMedicalRecord(person.getFirstName(), person.getLastName());
                                return new FireDTO(
                                        person.getFirstName(),
                                        person.getLastName(),
                                        person.getPhone(),
                                        medicalRecord.getMedications(),
                                        medicalRecord.getAllergies(),
                                        medicalrecordService.agePerson(medicalRecord.getBirthdate()),
                                        firestation.getStation()
                                );
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                ).toList();
        return fireDTOList;
    }
    public List<FireDTO> getListPersonByStationNumber(List<String> listStation_number) throws IOException {
      listStation_number = new ArrayList<>();
      List<Person> personList = new ArrayList<>();

        List<Firestation>  listFirestations = firestationService.getListFirestations();
        for (int i = 0; i < listFirestations.size(); i++) {
            listStation_number.addAll(listFirestations.stream().map(firestation -> (firestation.getStation())).toList());
            List<Firestation> firestations = firestationService.getAllFirestationByStationNumber(listStation_number.get(i));
            personList = personService.getListPersonByAddress(firestations.get(i).getAddress());
        }
        List<FireDTO> infoPersonAndMedicalRicordDTOS = personList.stream()
                .map(person -> {
                            try {
                                MedicalRecord medicalRecord = medicalrecordService.getMedicalRecord(person.getFirstName(), person.getLastName());
                                return new FireDTO(
                                        person.getFirstName(),
                                        person.getLastName(),
                                        person.getAddress(),
                                        medicalRecord.getMedications(),
                                        medicalRecord.getAllergies(),
                                        medicalrecordService.agePerson(medicalRecord.getBirthdate()),
                                        null
                                    );
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                ).toList();
        return infoPersonAndMedicalRicordDTOS;

    }
}
