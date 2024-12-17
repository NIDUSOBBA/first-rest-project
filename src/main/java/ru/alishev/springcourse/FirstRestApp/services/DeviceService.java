package ru.alishev.springcourse.FirstRestApp.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.alishev.springcourse.FirstRestApp.dto.DeviceDto;
import ru.alishev.springcourse.FirstRestApp.exceptions.SensorNamingException;
import ru.alishev.springcourse.FirstRestApp.exceptions.SensorRepetitionException;
import ru.alishev.springcourse.FirstRestApp.exceptions.SensorResponseException;
import ru.alishev.springcourse.FirstRestApp.models.Device;
import ru.alishev.springcourse.FirstRestApp.repositories.DeviceRepository;
import ru.alishev.springcourse.FirstRestApp.util.DeviceValidator;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class DeviceService {
    private final DeviceRepository deviceRepository;
    private final DeviceValidator deviceValidator;
    private final ModelMapper modelMapper;

    @Autowired
    public DeviceService(DeviceRepository deviceRepository, DeviceValidator deviceValidator, ModelMapper modelMapper) {
        this.deviceRepository = deviceRepository;
        this.deviceValidator = deviceValidator;
        this.modelMapper = modelMapper;
    }

    public Device getSensorByName(String name) {
        return deviceRepository.getSensorByName(name);
    }

    @Transactional
    public void save(Device device) {
        device.setCreatedAt(LocalDateTime.now());
        device.setCreatedWhi("WeatherSensor");
        deviceRepository.save(device);
    }

    public List<Device> getAll() {
        return deviceRepository.findAll();
    }

    @Transactional
    public void save(DeviceDto deviceDto, BindingResult bindingResult){
        deviceValidator.validate(deviceDto, bindingResult);
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
        if(deviceDto.getName() == null || deviceDto.getName().length() < 2 ) {
            throw new SensorNamingException("Not a valid name");
        }

        deviceRepository.save(modelMapper.map(deviceDto, Device.class));
    }

    @ExceptionHandler
    private ResponseEntity<SensorResponseException> handleException(SensorRepetitionException e) {
        SensorResponseException response = new SensorResponseException(
                "Device with that name already exists",
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
