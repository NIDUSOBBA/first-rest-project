package ru.alishev.springcourse.FirstRestApp.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.alishev.springcourse.FirstRestApp.dto.MeasurementsDto;

@Component
public class MeasurementsValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        MeasurementsDto measurementsDto = (MeasurementsDto) target;

        if (measurementsDto.getValue() == null || measurementsDto.getDevice() == null || measurementsDto.getRaining() == null) {
            throw new IllegalArgumentException("Invalid measurements. One of the three values or all of them are invalid");
        }
        if (measurementsDto.getValue() > 100 || measurementsDto.getValue() < -100) {
            throw new IllegalArgumentException("Invalid measurements. One of the three values is out of range");
        }
    }
}
