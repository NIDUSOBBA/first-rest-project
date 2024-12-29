package ru.alishev.springcourse.FirstRestApp;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Random;

@SpringBootApplication
public class FirstRestAppApplication {

	public Random random = new Random();

	public static void main(String[] args) {
		SpringApplication.run(FirstRestAppApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
