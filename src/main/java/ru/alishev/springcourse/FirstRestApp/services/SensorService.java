package ru.alishev.springcourse.FirstRestApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alishev.springcourse.FirstRestApp.models.Sensor;
import ru.alishev.springcourse.FirstRestApp.repositories.SensorRepository;

import java.time.LocalDateTime;

@Service
@Transactional(readOnly = true)
public class SensorService {
    private final SensorRepository sensorRepository;

    @Autowired
    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    public Sensor getSensorByName(String name) {
        return sensorRepository.getSensorByName(name);
    }

    @Transactional
    public void save(Sensor sensor) {
        sensor.setCreatedAt(LocalDateTime.now());
        sensor.setCreatedWhi("WeatherSensor");
        sensorRepository.save(sensor);
    }


}
