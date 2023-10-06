package com.safetynet.alerts.safetynetalerts.service;

import com.safetynet.alerts.safetynetalerts.entity.Data;
import com.safetynet.alerts.safetynetalerts.entity.Firestation;
import com.safetynet.alerts.safetynetalerts.entity.MedicalRecord;
import com.safetynet.alerts.safetynetalerts.entity.Person;
import com.safetynet.alerts.safetynetalerts.repository.MedicalrecordRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Paths;
import java.time.Year;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class MedicalrecordService implements MedicalrecordRepository {
    private final DataService dataService;
    private final  String FILE_NAME = "src/main/resources/data.json";
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
            List<MedicalRecord> medicalRecordList = getListMedicalRecords();
            Optional<MedicalRecord> optionalMedicalRecord = medicalRecordList.stream()
                    .filter(medicalRecord -> medicalRecord.getFirstName().equals(firstName) && medicalRecord.getLastName().equals(lastName))
                    .findFirst();
            if (optionalMedicalRecord.isPresent()){
                return optionalMedicalRecord.get();
            }
            return  null;
    }

    /**
     * @param medicalRecord
     * @return
     * @throws IOException
     */
    @Override
    public MedicalRecord updateMedicalRecord(MedicalRecord medicalRecord) throws IOException {
            int cpt = 0;
            listMedicalRecord  = getListMedicalRecords();
            String firstName = null;
            for (int i = 0; i < listMedicalRecord.size(); i++) {
                if (listMedicalRecord.get(i).getFirstName() == (medicalRecord.getFirstName())) {
                    firstName = medicalRecord.getFirstName();
                    cpt = i;
                    break;
                }
            }

            MedicalRecord medicalRecord1 = new MedicalRecord();
            medicalRecord1.setFirstName(firstName);
            medicalRecord1.setLastName(medicalRecord.getLastName());
            medicalRecord1.setBirthdate(medicalRecord.getBirthdate());
            medicalRecord1.setMedications(medicalRecord.getMedications());
            medicalRecord1.setAllergies(medicalRecord.getAllergies());
            listMedicalRecord.set(cpt, medicalRecord);
            Data data =  buildData(dataService.getData(Paths.get(FILE_NAME)).getPersons(), listMedicalRecord, dataService.getData(Paths.get(FILE_NAME)).getFirestations());
            dataService.setData(data,Paths.get(FILE_NAME));
            return medicalRecord1;
    }
    /**
     * @param firstName
     * @param lastNme
     * @return
     * @throws IOException
     */
    @Override
    public boolean deleteMedicalRecord(String firstName, String lastNme) throws IOException {
            List<MedicalRecord> medicalRecordList = getListMedicalRecords();
            medicalRecordList.removeIf(medicalRecord -> medicalRecord.getFirstName() == (firstName) && medicalRecord.getLastName()==(lastNme));
            return true;
        }

    private Data buildData(List<Person> persons,List<MedicalRecord> medicalrecords,List<Firestation> firestations){

        Data data = new Data();
        data.setPersons(persons);
        data.setMedicalrecords(medicalrecords);
        data.setFirestations(firestations);
        return data;
    }
    public int agePerson(String birthdate) {
                int annee = Integer.parseInt(birthdate.split("/")[2]);
                int anneeEnCours = Year.now().getValue();
                int age = anneeEnCours - annee;
                return age;
    }
    public int  getNombreDAdulte(List<Person> personList) throws IOException {
        int cptAdulte =0;
        for (Person person: personList){

                MedicalRecord medicalRecord = getMedicalRecord(person.getFirstName(), person.getLastName());
               String birthdate =  medicalRecord.getBirthdate();
               int age = agePerson(birthdate);
               if(age >=18) {
                   cptAdulte++;
               }
        }
        return  cptAdulte;
    }
    public int  getNombreEnfant(List<Person> personList) throws IOException {
        int cptEnfant =0;
        for (Person person: personList){
            try {
                MedicalRecord medicalRecord = getMedicalRecord(person.getFirstName(), person.getLastName());
                String birthdate =  medicalRecord.getBirthdate();
                int age = agePerson(birthdate);
                if(age <18){
                    cptEnfant++;
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return  cptEnfant;
    }

}
