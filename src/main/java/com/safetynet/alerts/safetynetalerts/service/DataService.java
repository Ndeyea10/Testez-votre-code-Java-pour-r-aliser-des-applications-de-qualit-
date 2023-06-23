package com.safetynet.alerts.safetynetalerts.service;

import ch.qos.logback.core.util.FileUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.safetynetalerts.entity.Data;
import com.safetynet.alerts.safetynetalerts.entity.Person;
import com.safetynet.alerts.safetynetalerts.repository.IDataService;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

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
      //  String FILE_NAME_TEST = "src/test/resources/dataTest.json";
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.valueToTree(data);
            String jsonString = jsonNode.toString();
            //String content = Files.readString(path);

            //Write content to file
         //   jsonNode.with(FILE_NAME_TEST);
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
