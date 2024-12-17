package ru.alishev.springcourse.FirstRestApp.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.alishev.springcourse.FirstRestApp.dto.SensorDTO;
import ru.alishev.springcourse.FirstRestApp.models.Sensor;
import ru.alishev.springcourse.FirstRestApp.services.SensorService;
import ru.alishev.springcourse.FirstRestApp.util.SensorNamingException;
import ru.alishev.springcourse.FirstRestApp.util.SensorRepetitionException;
import ru.alishev.springcourse.FirstRestApp.util.SensorResponseException;
import ru.alishev.springcourse.FirstRestApp.util.SensorValidator;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/device")
public class DeviceController {

    private final SensorService sensorService;
    private final ModelMapper modelMapper;
    private final SensorValidator sensorValidator;

    @Autowired
    public DeviceController(SensorService sensorService, ModelMapper modelMapper, SensorValidator sensorValidator) {
        this.sensorService = sensorService;
        this.modelMapper = modelMapper;
        this.sensorValidator = sensorValidator;
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> registrationSignal(@RequestBody SensorDTO sensorDTO,
                                                         BindingResult bindingResult) {
        sensorValidator.validate(sensorDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMsg.append(error.getField())
                        .append(" - ").append(error.getDefaultMessage())
                        .append(";");
            }

            throw new SensorRepetitionException(errorMsg.toString());

        }
        if(sensorDTO.getName() == null || sensorDTO.getName().length() < 2 ) {
            throw new SensorNamingException("Not a valid name");
        }

        sensorService.save(modelMapper.map(sensorDTO, Sensor.class));

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<SensorResponseException> handleException(SensorRepetitionException e) {
        SensorResponseException response = new SensorResponseException(
                "Sensor with that name already exists",
                LocalDateTime.now()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler
    private ResponseEntity<SensorResponseException> handleException(SensorNamingException e) {
        SensorResponseException response = new SensorResponseException(
                "The name is too short or does not exist at all, I recall the name from 2 to 30 characters",
                LocalDateTime.now()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
