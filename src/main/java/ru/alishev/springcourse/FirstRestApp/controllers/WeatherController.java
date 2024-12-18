package ru.alishev.springcourse.FirstRestApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ru.alishev.springcourse.FirstRestApp.dto.MeasurementsDto;
import ru.alishev.springcourse.FirstRestApp.dto.VisualMeasurementsDto;
import ru.alishev.springcourse.FirstRestApp.services.MeasurementsService;
import ru.alishev.springcourse.FirstRestApp.util.MeasurementsValidator;

import java.util.List;

@RestController
public class WeatherController {
    private final MeasurementsService measurementsService;

    @Autowired
    public WeatherController(MeasurementsService measurementsService) {
        this.measurementsService = measurementsService;
    }

    @GetMapping("/measurements/rainyDaysCount")
    public List<VisualMeasurementsDto> getRainyDaysCount() {

        // сделать через sream api | all.stream()
        return measurementsService.findByMeasurementsIsRaining().stream().map(measurementsService::creatVisualDto).toList();
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setValidator(new MeasurementsValidator());
    }

    @PostMapping("/measurements/add")
    public ResponseEntity<HttpStatus> newForecast(@Validated @RequestBody MeasurementsDto measurementsDTO,
                                                  BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST);
        }
        measurementsService.save(measurementsDTO);

        return ResponseEntity.ok(HttpStatus.OK);
    }
}
