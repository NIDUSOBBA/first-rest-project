package ru.alishev.springcourse.FirstRestApp.util;

import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import ru.alishev.springcourse.FirstRestApp.dto.MeasurementsDto;

import java.util.HashMap;
import java.util.Map;

public class CompletingTask {

    public CompletingTask() {
        System.out.println("CompletingTask");
    }

    @PostConstruct
    public void init(){
        RestTemplate template = new RestTemplate();
        Randomized randomaser = new Randomized();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String getAllMeasurements = "http://localhost:8080/measurements";

        String postAddMeasurements = "http://localhost:8080/measurements/add";
        String postRegistrationSensor = "http://localhost:8080/sensor/registration";

        for (int i = 1; i < 4; i++){
            Map<String,String> sensor= new HashMap<>();
            sensor.put("name","Test"+i);
            HttpEntity<Map<String,String>> requestSensor = new HttpEntity<>(sensor);
            template.postForEntity(postRegistrationSensor, requestSensor, String.class);
        }
        for (int i = 0; i < 1000; i++){
            MeasurementsDto temp = new MeasurementsDto();
            temp.setValue(randomaser.randomValue());
            temp.setRaining(randomaser.randomRaining());
            temp.setDivece(randomaser.randomSensor());
            HttpEntity<MeasurementsDto> request = new HttpEntity<>(temp, headers);
            template.postForEntity(postAddMeasurements, request, String.class);
        }

        System.out.println(template.getForObject(getAllMeasurements, String.class));
    }
}
