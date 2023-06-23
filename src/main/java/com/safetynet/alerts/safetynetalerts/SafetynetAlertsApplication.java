package com.safetynet.alerts.safetynetalerts;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.safetynetalerts.entity.Person;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SafetynetAlertsApplication {

	public static void main(String[] args) throws IOException {
		/*JsonNode persons = readJsonFile();
		List<Person> listPerson = mapPerson(persons);
		System.out.println(listPerson);
		SpringApplication.run(SafetynetAlertsApplication.class, args);

	}
	 private static List<Person> mapPerson(JsonNode persons){
		 List<Person> result = new ArrayList<>();

		 return result;
	 }

	 private static JsonNode readJsonFile() throws IOException {
		 ObjectMapper mapper = new ObjectMapper();
		 JsonNode file = mapper.readTree(SafetynetAlertsApplication.class.getResource("/data.json"));

		 return  file;*/
	 }
}
