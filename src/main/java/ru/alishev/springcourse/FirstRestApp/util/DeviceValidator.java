package ru.alishev.springcourse.FirstRestApp.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ru.alishev.springcourse.FirstRestApp.dto.DeviceDto;
import ru.alishev.springcourse.FirstRestApp.services.DeviceService;

@Component
public class DeviceValidator implements Validator {

    private final DeviceService deviceService;

    public DeviceValidator(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return DeviceDto.class.equals(clazz) ;
    }

    @Override
    public void validate(Object target, Errors errors) {
        DeviceDto deviceDto = (DeviceDto) target ;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "field.required", "Name cannot be empty");

        if(deviceService.getSensorByName(deviceDto.getName()) != null) {
                throw new IllegalArgumentException("Device with name '" + deviceDto.getName() + "' already exists!");
        }
        if(deviceDto.getName().length() < 2){
            throw new IllegalArgumentException("The name must be between 2 and 30 characters long!");
        }


    }
}
