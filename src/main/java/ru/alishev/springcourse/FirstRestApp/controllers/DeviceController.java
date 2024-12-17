package ru.alishev.springcourse.FirstRestApp.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.alishev.springcourse.FirstRestApp.dto.DeviceDto;
import ru.alishev.springcourse.FirstRestApp.services.DeviceService;
import ru.alishev.springcourse.FirstRestApp.util.DeviceValidator;

@RestController
@RequestMapping("/device")
public class DeviceController {

    private final DeviceService deviceService;
    private final ModelMapper modelMapper;
    private final DeviceValidator deviceValidator;

    @Autowired
    public DeviceController(DeviceService deviceService, ModelMapper modelMapper, DeviceValidator deviceValidator) {
        this.deviceService = deviceService;
        this.modelMapper = modelMapper;
        this.deviceValidator = deviceValidator;
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> registrationSignal(@RequestBody @Validated DeviceDto deviceDto,
                                                         BindingResult bindingResult) {
        deviceService.save(deviceDto, bindingResult);

        return ResponseEntity.ok(HttpStatus.OK);
    }


}
