package ru.alishev.springcourse.FirstRestApp.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.alishev.springcourse.FirstRestApp.dto.MeasurementsDto;
import ru.alishev.springcourse.FirstRestApp.dto.VisualMeasurementsDto;
import ru.alishev.springcourse.FirstRestApp.models.Measurements;
import ru.alishev.springcourse.FirstRestApp.services.MeasurementsService;
import ru.alishev.springcourse.FirstRestApp.services.DeviceService;
import ru.alishev.springcourse.FirstRestApp.exceptions.MeasurementsEmptyException;
import ru.alishev.springcourse.FirstRestApp.exceptions.MeasurementsOutOfBoundsException;
import ru.alishev.springcourse.FirstRestApp.exceptions.MeasurementsResponseException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/measurements")
public class MeasurementsController {

    private final MeasurementsService measurementsService;
    private final ModelMapper modelMapper;
    private final DeviceService deviceService;

    @Autowired
    public MeasurementsController(MeasurementsService measurementsService, ModelMapper modelMapper, DeviceService deviceService) {
        this.measurementsService = measurementsService;
        this.modelMapper = modelMapper;
        this.deviceService = deviceService;
    }

    @GetMapping
    public List<VisualMeasurementsDto> getAllMeasurements() {

        // сделать через sream api | all.stream()
        return measurementsService.findAll().stream().map(measurementsService::creatVisualDto).toList();
    }

    @GetMapping("/rainyDaysCount")
    public List<VisualMeasurementsDto> getRainyDaysCount() {

        // сделать через sream api | all.stream()
        return measurementsService.findByMeasurementsIsRaining().stream().map(measurementsService::creatVisualDto).toList();
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> newForecast(@RequestBody MeasurementsDto measurementsDTO) {
        measurementsService.save(measurementsDTO);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<MeasurementsResponseException> handleException(MeasurementsEmptyException e) {
        MeasurementsResponseException response = new MeasurementsResponseException(
                "Any of the values or all of them are empty, please fill in each of the parameters value, double and Device",

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
