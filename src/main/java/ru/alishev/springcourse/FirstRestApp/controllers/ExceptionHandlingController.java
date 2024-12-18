package ru.alishev.springcourse.FirstRestApp.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.alishev.springcourse.FirstRestApp.exceptions.*;

import java.time.LocalDateTime;

@ControllerAdvice
public class ExceptionHandlingController {

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

    @ExceptionHandler
    private ResponseEntity<SensorResponseException> handleException(DeviceRepetitionException e) {
        SensorResponseException response = new SensorResponseException(
                "Device with that name already exists",
                LocalDateTime.now()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler
    private ResponseEntity<SensorResponseException> handleException(DeviceNamingException e) {
        SensorResponseException response = new SensorResponseException(
                "The name is too short or does not exist at all, I recall the name from 2 to 30 characters",
                LocalDateTime.now()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
