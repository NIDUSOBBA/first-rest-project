package ru.alishev.springcourse.FirstRestApp;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.alishev.springcourse.FirstRestApp.util.CompletingTask;

import java.util.Random;

@SpringBootApplication
public class FirstRestAppApplication {

	public Random random = new Random();

	public static void main(String[] args) {
		SpringApplication.run(FirstRestAppApplication.class, args);

		// убрать ищ main, реализовать эту логику в отдельном классе и вызывать через @PostConstruct
		CompletingTask completingTask = new CompletingTask();

	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}


}
