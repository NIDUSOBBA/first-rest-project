package ru.alishev.springcourse.FirstRestApp;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import ru.alishev.springcourse.FirstRestApp.dto.MeasurementsDTO;
import ru.alishev.springcourse.FirstRestApp.util.Randomaser;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@SpringBootApplication
public class FirstRestAppApplication {

	public Random random = new Random();

	public static void main(String[] args) {
		SpringApplication.run(FirstRestAppApplication.class, args);

		// убрать ищ main, реализовать эту логику в отдельном классе и вызывать через @PostConstruct
		RestTemplate template = new RestTemplate();
		Randomaser randomaser = new Randomaser();

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
			MeasurementsDTO temp = new MeasurementsDTO();
			temp.setValue(randomaser.randomValue());
			temp.setRaining(randomaser.randomRaining());
			temp.setSensor(randomaser.randomSensor());
			HttpEntity<MeasurementsDTO> request = new HttpEntity<>(temp, headers);
			template.postForEntity(postAddMeasurements, request, String.class);
		}

		System.out.println(template.getForObject(getAllMeasurements, String.class));

	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}


}
