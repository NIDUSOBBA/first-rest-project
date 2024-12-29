package ru.alishev.springcourse.FirstRestApp.util;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.alishev.springcourse.FirstRestApp.dto.MeasurementsDto;

import java.util.HashMap;
import java.util.Map;

@Component
public class CompletingTask {

    private final RestTemplate template;

    @Autowired
    public CompletingTask(RestTemplate template) {
        this.template = template;
    }

    @PostConstruct
    public void init() {
        Randomized randomized = new Randomized();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String getAllMeasurements = "http://localhost:8080/measurements";

        String postAddMeasurements = "http://localhost:8080/measurements/add";
        String postRegistrationSensor = "http://localhost:8080/device/registration";

        for (int i = 1; i < 4; i++) {
            Map<String, String> sensor = new HashMap<>();
            sensor.put("name", "Test" + i);
            HttpEntity<Map<String, String>> requestSensor = new HttpEntity<>(sensor);
            template.postForEntity(postRegistrationSensor, requestSensor, String.class);
        }
        for (int i = 0; i < 1000; i++) {
            MeasurementsDto temp = new MeasurementsDto();
            temp.setValue(randomized.randomValue());
            temp.setRaining(randomized.randomRaining());
            temp.setDevice(randomized.randomSensor());
            HttpEntity<MeasurementsDto> request = new HttpEntity<>(temp, headers);
            template.postForEntity(postAddMeasurements, request, String.class);
        }
        System.out.println(template.getForObject(getAllMeasurements, String.class));
    }
}
