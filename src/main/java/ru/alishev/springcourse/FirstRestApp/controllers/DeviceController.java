package ru.alishev.springcourse.FirstRestApp.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ru.alishev.springcourse.FirstRestApp.dto.DeviceDto;
import ru.alishev.springcourse.FirstRestApp.models.Device;
import ru.alishev.springcourse.FirstRestApp.services.DeviceService;
import ru.alishev.springcourse.FirstRestApp.util.DeviceValidator;

@RestController
@RequestMapping("/device")
public class DeviceController {

    private final DeviceService deviceService;
    private final ModelMapper modelMapper;


    @Autowired
    public DeviceController(DeviceService deviceService, ModelMapper modelMapper) {
        this.deviceService = deviceService;
        this.modelMapper = modelMapper;
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new DeviceValidator(deviceService));
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> registrationSignal(@RequestBody @Validated DeviceDto deviceDto,
                                                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST);
        }

        deviceService.save(modelMapper.map(deviceDto, Device.class));

        return ResponseEntity.ok(HttpStatus.OK);
    }


}
