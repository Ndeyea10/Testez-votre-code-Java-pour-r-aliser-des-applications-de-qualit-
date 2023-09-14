package com.safetynet.alerts.safetynetalerts.dto;

import com.safetynet.alerts.safetynetalerts.entity.MedicalRecord;
import lombok.AllArgsConstructor;
import lombok.Data;
@Data
public class MedicalRecordDTO {
    private  String firstName;
    private  String lastName;
    private  String address;
    private  String[] medications;
    private  String[] allergies;
    private int age;
    private String email;
}
