package com.safetynet.alerts.safetynetalerts.repository;

import com.safetynet.alerts.safetynetalerts.entity.Data;
import com.safetynet.alerts.safetynetalerts.entity.Person;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public interface IDataService {
    Data getData(Path path) throws IOException;
    void setData(Data data, Path path)throws IOException;


}
