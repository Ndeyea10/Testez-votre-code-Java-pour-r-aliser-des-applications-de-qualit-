package com.safetynet.alerts.safetynetalerts.dto;

import lombok.Data;

import java.util.List;
@Data
public class PersonByStationNumberDTO {
    List<PersonDTO> personDTOList;
    int nbAdulte;
    int nbEnfant;

}
