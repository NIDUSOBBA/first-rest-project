package ru.alishev.springcourse.FirstRestApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.alishev.springcourse.FirstRestApp.dto.VisualMeasurementsDto;
import ru.alishev.springcourse.FirstRestApp.services.MeasurementsService;

import java.util.List;

@RestController
@RequestMapping("/measurements")
public class MeasurementsController {

    private final MeasurementsService measurementsService;


    @Autowired
    public MeasurementsController(MeasurementsService measurementsService) {
        this.measurementsService = measurementsService;
    }

    @GetMapping
    public List<VisualMeasurementsDto> getAllMeasurements() {

        // сделать через sream api | all.stream()
        return measurementsService.findAll().stream().map(measurementsService::creatVisualDto).toList();
    }





}
