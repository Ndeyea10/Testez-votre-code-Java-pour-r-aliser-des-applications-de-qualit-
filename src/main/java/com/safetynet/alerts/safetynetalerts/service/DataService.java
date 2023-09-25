package com.safetynet.alerts.safetynetalerts.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.safetynetalerts.entity.Data;
import com.safetynet.alerts.safetynetalerts.repository.IDataService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class DataService implements IDataService {
    @Override
    public Data getData(Path path) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String content = Files.readString(path);
        byte[] bytes = Files.readString(path).getBytes();
        Data data = mapper.readValue(content, Data.class);
        //Data data = mapper.readValue(new String(bytes), Data.class);
        return data;
    }

    @Override
    public void setData(Data data, Path path) throws IOException {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.valueToTree(data);
            String jsonString = jsonNode.toString();

            //Write content to file
            if(!Files.exists(path)) {
                Files.createFile(path);
            }
            Files.write(path, jsonString.getBytes());

            //Verify file content
            String content = Files.readString(path);
            System.out.println(content);
        }catch(IOException e)
            {
                e.printStackTrace();
            }
    }

}
