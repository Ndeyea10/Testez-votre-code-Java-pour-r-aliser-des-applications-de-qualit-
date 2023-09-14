package com.safetynet.alerts.safetynetalerts.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
@AllArgsConstructor
@Data
public class FireDTO {
    private  String firstName;
    private  String lastName;
    private  String phone;
    private  String[] medications;
    private  String[] allergies;
    private int age;
    private String stationNumber;

}
