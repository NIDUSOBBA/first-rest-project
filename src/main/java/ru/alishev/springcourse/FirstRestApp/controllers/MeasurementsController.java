package ru.alishev.springcourse.FirstRestApp.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.alishev.springcourse.FirstRestApp.dto.MeasurementsDTO;
import ru.alishev.springcourse.FirstRestApp.dto.VisualMeasurementsDto;
import ru.alishev.springcourse.FirstRestApp.models.Measurements;
import ru.alishev.springcourse.FirstRestApp.services.MeasurementsService;
import ru.alishev.springcourse.FirstRestApp.services.SensorService;
import ru.alishev.springcourse.FirstRestApp.util.MeasurementsEmptyException;
import ru.alishev.springcourse.FirstRestApp.util.MeasurementsOutOfBoundsException;
import ru.alishev.springcourse.FirstRestApp.util.MeasurementsResponseException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/measurements")
public class MeasurementsController {

    private final MeasurementsService measurementsService;
    private final ModelMapper modelMapper;
    private final SensorService sensorService;

    @Autowired
    public MeasurementsController(MeasurementsService measurementsService, ModelMapper modelMapper, SensorService sensorService) {
        this.measurementsService = measurementsService;
        this.modelMapper = modelMapper;
        this.sensorService = sensorService;
    }

    @GetMapping
    public List<VisualMeasurementsDto> getAllMeasurements() {

        // сделать через sream api | all.stream()
        List<Measurements> all = measurementsService.findAll();
        List<VisualMeasurementsDto> dtos = new ArrayList<>();

        for (Measurements measurements : all) {
            dtos.add(measurementsService.creatVisualDto(measurements));
        }
        return dtos;
    }

    @GetMapping("/rainyDaysCount")
    public List<VisualMeasurementsDto> getRainyDaysCount() {
        // сделать через sream api | all.stream()
        List<Measurements> byMeasurementsIsRaining = measurementsService.findByMeasurementsIsRaining();
        List<VisualMeasurementsDto> rainDay= new ArrayList<>();
        for (Measurements measurements : byMeasurementsIsRaining) {
            rainDay.add(measurementsService.creatVisualDto(measurements));
        }
        return rainDay;
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> newForecast(@RequestBody MeasurementsDTO measurementsDTO) {
        if (measurementsDTO.getValue() == null || measurementsDTO.getSensor() == null || measurementsDTO.isRaining() == null) {
            throw new MeasurementsEmptyException();
        }
        if (measurementsDTO.getValue() > 100 || measurementsDTO.getValue() < -100) {
            throw new MeasurementsOutOfBoundsException();
        }
        Measurements map = modelMapper.map(measurementsDTO, Measurements.class);
        map.setSensor(sensorService.getSensorByName(measurementsDTO.getSensor().getName()));

        measurementsService.save(map);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<MeasurementsResponseException> handleException(MeasurementsEmptyException e) {
        MeasurementsResponseException response = new MeasurementsResponseException(
                "Any of the values or all of them are empty, please fill in each of the parameters value, double and Sensor",

 LocalDateTime.now()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<MeasurementsResponseException> handleException(MeasurementsOutOfBoundsException e) {
        MeasurementsResponseException response = new MeasurementsResponseException(
                "The range for value is from +100 to -100",
                LocalDateTime.now()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
