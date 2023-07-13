package com.safetynet.alerts.safetynetalerts;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.safetynetalerts.entity.Person;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@EnableAutoConfiguration
@SpringBootApplication
public class SafetynetAlertsApplication extends SpringBootServletInitializer {

	private static boolean startHasbBeenLaunched = false;
	public static void main(String[] args) throws IOException {
		SpringApplication.run(SafetynetAlertsApplication.class, args);
		startHasbBeenLaunched = true;

	 }
	 public static boolean startHasbBeenLaunched(){
		return startHasbBeenLaunched;
	 }
}
