package com.safetynet.alerts.safetynetalerts.service;

import com.safetynet.alerts.safetynetalerts.entity.Data;
import com.safetynet.alerts.safetynetalerts.entity.Firestation;
import com.safetynet.alerts.safetynetalerts.entity.MedicalRecord;
import com.safetynet.alerts.safetynetalerts.entity.Person;
import com.safetynet.alerts.safetynetalerts.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PersonService implements PersonRepository {
    private final DataService dataService;
    private final  String FILE_NAME = "src/main/resources/data.json";
    private List<Person> listPerson;
    public PersonService(DataService dataService) {this.dataService = dataService;}
    @Override
    public List<Person> getListPersons() throws IOException {
        listPerson = dataService.getData(Paths.get(FILE_NAME)).getPersons();
          // System.out.println(listPerson);
        return listPerson;
    }
    public Person updatePerson(Person person) throws IOException {
        int cpt = 0;
        listPerson  = getListPersons();
        String firstName = null;
        for (int i = 0; i < listPerson.size(); i++) {
            if (listPerson.get(i).getFirstName() == (person.getFirstName())) {
                firstName = person.getFirstName();
                cpt = i;
                break;
            }
        }

        Person person1 = new Person();
        person1.setFirstName(firstName);
        person1.setLastName(person.getLastName());
        person1.setAddress(person.getAddress());
        person1.setEmail(person.getEmail());
        person1.setCity(person.getCity());
        person1.setPhone(person.getPhone());
        person1.setZip(person.getZip());
        listPerson.set(cpt, person);
       Data data =  buildData(listPerson, dataService.getData(Paths.get(FILE_NAME)).getMedicalrecords(), dataService.getData(Paths.get(FILE_NAME)).getFirestations());
       dataService.setData(data,Paths.get(FILE_NAME));
       return person1;
    }
    @Override
    public Person getPersonByFirstNameAndLastName(String firstName, String lastName) throws IOException {
        List<Person> listPersons = getListPersons();
        Optional<Person> personOptional = listPersons.stream()
                .filter(person -> person.getFirstName().equals(firstName) && person.getLastName().equals(lastName))
                .findFirst();
        if (personOptional.isPresent()){
            return personOptional.get();
        }
        return  null;
    }

    @Override
    public boolean deletePerson(String firstName, String lastNme) throws IOException {
        List<Person> listPerson = getListPersons();
        listPerson.removeIf(person -> person.getFirstName() == (firstName) && person.getLastName()==(lastNme));
        // System.out.println("Person deleted.");
        return true;
    }

    private Data buildData(List<Person> persons,List<MedicalRecord> medicalrecords,List<Firestation> firestations){

        Data data = new Data();
        data.setPersons(persons);
        data.setMedicalrecords(medicalrecords);
        data.setFirestations(firestations);
        return data;
    }

    public  List<Person> getListPersonByAddress(String address) throws IOException {
        List<Person> listPerson = getListPersons();
       return listPerson.stream().filter(person -> person.getAddress().equals(address)).toList();
    }
    public  List<Person>  getPersonByCity(String city) throws IOException {
        List<Person> listPerson = getListPersons();
        return listPerson.stream().filter(person -> person.getCity().equals(city)).toList();

    }
    public  List<Person> getListPersonByEmail(String email) throws IOException {
        List<Person> listPerson = getListPersons();
        return listPerson.stream().filter(person -> person.getEmail().equals(email)).toList();
    }

    public Person getPersonByCities(String city) throws IOException {
        List<Person> listPerson = getListPersons();
        Optional<Person> optionalPerson= listPerson.stream().filter(person -> person.getCity().equals(city)).
                findFirst();
        if (optionalPerson.isPresent()){
            return optionalPerson.get();
        }
        return  null;
    }
}
