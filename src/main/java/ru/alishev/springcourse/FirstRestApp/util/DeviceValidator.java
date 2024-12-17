package ru.alishev.springcourse.FirstRestApp.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.alishev.springcourse.FirstRestApp.dto.DeviceDto;
import ru.alishev.springcourse.FirstRestApp.services.DeviceService;

@Component
public class DeviceValidator implements Validator {

    private final DeviceService deviceService;

    @Autowired
    public DeviceValidator(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return DeviceDto.class.equals(clazz) ;
    }

    @Override
    public void validate(Object target, Errors errors) {
        DeviceDto sensor = (DeviceDto) target ;
        if(deviceService.getSensorByName(sensor.getName()) != null) {
            errors.rejectValue("name", "", "Device with name " + sensor.getName() + " already exists");
        }

    }
}
